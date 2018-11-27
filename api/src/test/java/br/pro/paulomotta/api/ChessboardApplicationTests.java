package br.pro.paulomotta.api;

import br.pro.paulomotta.poc.Chessboard;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;
import org.json.JSONException;
import static org.junit.Assert.assertEquals;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/**
 *
 * @author paulo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChessboardApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldReturn200WhenSendingRequestToController() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<List> entity = this.testRestTemplate.getForEntity(
                createURLWithPort("/moves/Kt/h1/1"), List.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void testRetrieveMoves() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/moves/Kt/h1/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"coordinates\":\"f2\"},{\"coordinates\":\"g3\"}]";

        String s = response.getBody();
        System.out.println("s="+s);
        JSONAssert.assertEquals(expected, s , false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    @Test
    public void testAddRemoveColumn() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Boolean> response = testRestTemplate.exchange(
                createURLWithPort("/add/column"),
                HttpMethod.POST, entity, Boolean.class);

        Boolean expected = true;

        Boolean b = response.getBody();
        System.out.println("s="+b);
        assertEquals(expected, b);
        
        response = testRestTemplate.exchange(
                createURLWithPort("/remove/column"),
                HttpMethod.POST, entity, Boolean.class);

        expected = true;

        b = response.getBody();
        System.out.println("s="+b);
        assertEquals(expected, b);
    }
    
    @Test
    public void testAddRemoveManyColumn() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        for(int i=Chessboard.MIN_COLUMNS; i < Chessboard.MAX_COLUMNS; i++){
            ResponseEntity<Boolean> response = testRestTemplate.exchange(
                    createURLWithPort("/add/column"),
                    HttpMethod.POST, entity, Boolean.class);

            Boolean expected = true;

            Boolean b = response.getBody();
            System.out.println("s="+b);
            assertEquals(expected, b);
        }
        
        ResponseEntity<Boolean> lastResponse = testRestTemplate.exchange(
                    createURLWithPort("/add/column"),
                    HttpMethod.POST, entity, Boolean.class);

        Boolean lastExpected = false;

        Boolean lastBody = lastResponse.getBody();
        System.out.println("s="+lastBody);
        assertEquals(lastExpected, lastBody);
        
        for(int i=Chessboard.MIN_COLUMNS; i < Chessboard.MAX_COLUMNS; i++){
            ResponseEntity<Boolean> response = testRestTemplate.exchange(
                    createURLWithPort("/remove/column"),
                    HttpMethod.POST, entity, Boolean.class);

            Boolean expected = true;

            Boolean b = response.getBody();
            System.out.println("s="+b);
            assertEquals(expected, b);
        }
        
        ResponseEntity<Boolean> response = testRestTemplate.exchange(
                    createURLWithPort("/remove/column"),
                    HttpMethod.POST, entity, Boolean.class);

        Boolean expected = false;

        Boolean b = response.getBody();
        System.out.println("s="+b);
        assertEquals(expected, b);
    }

    @Test
    public void testAddRemoveRow() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Boolean> response = testRestTemplate.exchange(
                createURLWithPort("/add/row"),
                HttpMethod.POST, entity, Boolean.class);

        Boolean expected = true;

        Boolean b = response.getBody();
        System.out.println("s="+b);
        assertEquals(expected, b);
        
        response = testRestTemplate.exchange(
                createURLWithPort("/remove/row"),
                HttpMethod.POST, entity, Boolean.class);

        expected = true;

        b = response.getBody();
        System.out.println("s="+b);
        assertEquals(expected, b);
    }
    
    @Test
    public void testAddRemoveManyRow() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        for(int i=Chessboard.MIN_ROWS; i < Chessboard.MAX_ROWS; i++){
            ResponseEntity<Boolean> response = testRestTemplate.exchange(
                    createURLWithPort("/add/row"),
                    HttpMethod.POST, entity, Boolean.class);

            Boolean expected = true;

            Boolean b = response.getBody();
            System.out.println("s="+b);
            assertEquals(expected, b);
        }
        
        ResponseEntity<Boolean> lastResponse = testRestTemplate.exchange(
                    createURLWithPort("/add/row"),
                    HttpMethod.POST, entity, Boolean.class);

        Boolean lastExpected = false;

        Boolean lastBody = lastResponse.getBody();
        System.out.println("s="+lastBody);
        assertEquals(lastExpected, lastBody);
        
        for(int i=Chessboard.MIN_ROWS; i < Chessboard.MAX_ROWS; i++){
            ResponseEntity<Boolean> response = testRestTemplate.exchange(
                    createURLWithPort("/remove/row"),
                    HttpMethod.POST, entity, Boolean.class);

            Boolean expected = true;

            Boolean b = response.getBody();
            System.out.println("s="+b);
            assertEquals(expected, b);
        }
        
        ResponseEntity<Boolean> response = testRestTemplate.exchange(
                    createURLWithPort("/remove/row"),
                    HttpMethod.POST, entity, Boolean.class);

        Boolean expected = false;

        Boolean b = response.getBody();
        System.out.println("s="+b);
        assertEquals(expected, b);
    }
}
