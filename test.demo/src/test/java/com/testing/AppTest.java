package com.testing;

import static org.junit.Assert.assertEquals;

import com.testing.model.LambdaResponse;
import org.json.simple.parser.ParseException;
import org.junit.Test;


public class AppTest {
  @Test
  public void successfulResponse() throws ParseException {
    App app = new App();
    LambdaResponse result = app.handleRequest(null, null);
    assertEquals("Hello World - CCAP Generated Lambda function", result.getBody());
  }
}
