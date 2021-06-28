package net.guides.enoca.enocaexample.controller;

import java.time.Instant;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.enoca.enocaexample.elasticsearch.ElasticSearchBinded;
import net.guides.enoca.enocaexample.model.Demand;
import net.guides.enoca.enocaexample.service.DemandService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/demand")
public class DemandController {
    @Autowired
    private DemandService demandService;

    @GetMapping
    public List<Demand> findAll() {
        return demandService.findAll();
    }

    @PostMapping
    public ResponseEntity<Demand> create(@Valid @RequestBody Demand demand) {
    	demand.setTimestamp(Instant.now().toString());
    	System.out.println("Time " + demand.getTimestamp());
    	ResponseEntity<Demand> temp = ResponseEntity.ok(demandService.createOrUpdate(demand));
    	ElasticSearchBinded.elasticSearch(demand);
    	return temp;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Demand> findById(@PathVariable long id) {

        Optional<Demand> demand = demandService.findById(id);

        if (!demand.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(demand.get());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Demand> update(@PathVariable long id, @Valid @RequestBody Demand demand2) {

        Optional<Demand> demand = demandService.findById(id);

        if (!demand.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        demand.get().setCustomerId(demand2.getCustomerId());
        demand.get().setProductId(demand2.getProductId());
        demand.get().setTimestamp(demand2.getTimestamp());
		final Demand updatedDemand= demandService.createOrUpdate(demand.get());
		return ResponseEntity.ok(updatedDemand);

       // return ResponseEntity.ok(demandService.createOrUpdate(demand2.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        Optional<Demand> p = demandService.findById(id);

        if (!p.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        demandService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
