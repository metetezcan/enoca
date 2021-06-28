package net.guides.enoca.enocaexample.elasticsearch;

import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import net.guides.enoca.enocaexample.model.*;

public class ElasticSearchBinded {
	private static Settings settings = null;
	private static TransportClient client = null;
	public static void elasticSearch(Object data)
	{
		String className = data.getClass().toString();
		String classNameShort = className.substring(className.lastIndexOf(".")+1,className.length());
		if(settings==null)
			settings=Settings.builder().put("cluster.name","elasticsearch").build();		
		try {
			if(client==null)
			{
				client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
			}
			
			if(classNameShort.equalsIgnoreCase("Customer"))
			{
				Customer temp = (Customer) data;
				Map<String,Object> json = new HashMap<>();
				json.put("id",temp.getId());
				json.put("name",temp.getName());
				json.put("surname",temp.getSurname());
				json.put("address",temp.getAddress());
				json.put("phone",temp.getPhone());
				IndexResponse indexResponse=client.prepareIndex("NSQL1","table1","1").setSource(json,XContentType.JSON).get();
				
			}
			else if(classNameShort.equalsIgnoreCase("Demand"))
			{
				Demand temp = (Demand) data;
				Map<String,Object> json = new HashMap<>();
				json.put("id",temp.getId());
				json.put("customerId",temp.getCustomerId());
				json.put("timestamp",temp.getTimestamp());
				json.put("productId",temp.getProductId());
				IndexResponse indexResponse=client.prepareIndex("NSQL1","table1","2").setSource(json,XContentType.JSON).get();
				
			}
			else if(classNameShort.equalsIgnoreCase("Product"))
			{
				Product temp = (Product) data;
				Map<String,Object> json = new HashMap<>();
				json.put("id",temp.getId());
				json.put("name",temp.getName());
				json.put("code",temp.getCode());
				json.put("supplier",temp.getSupplier());
				IndexResponse indexResponse=null;client.prepareIndex("NSQL1","table1","3").setSource(json,XContentType.JSON).get();
				
			}
	        client.close();
	        client = null;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
