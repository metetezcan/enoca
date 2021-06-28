package net.guides.enoca.enocaexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.guides.enoca.enocaexample.model.Demand;
import net.guides.enoca.enocaexample.repository.DemandRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DemandService {
    @Autowired
    private DemandRepository DemandRepository;
    public List<Demand> findAll() {
        return DemandRepository.findAll();
    }
    public Optional<Demand> findById(long id) {
        return DemandRepository.findById(id);
    }
    public Demand createOrUpdate(Demand Demand) {
        return DemandRepository.save(Demand);
    }
    public void deleteById(long id) {
        DemandRepository.deleteById(id);
    }
}
