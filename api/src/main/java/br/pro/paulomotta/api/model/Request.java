package br.pro.paulomotta.api.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Entity class to persist the request and the corresponding result
 * 
 * @author paulo
 */
@Entity
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String request;
    @Lob
    private String result;

    protected Request() {}

    public Request(String request, String result) {
        this.request = request;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", request=" + request + ", result=" + result + '}';
    }
    
    
}
