package com.testing;

import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.testing.model.LambdaResponse;
import software.amazon.lambda.powertools.tracing.CaptureMode;
import software.amazon.lambda.powertools.tracing.Tracing;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Map<String, Object>, LambdaResponse> {

    private static final String MESSAGE = "Hello World - CCAP Generated Lambda function";

    @Override
    @Tracing(captureMode = CaptureMode.RESPONSE_AND_ERROR)
    public LambdaResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        // Add code here. Below line inserts incoming event to DynamoDB
        DBHelper.persistItem(stringObjectMap);
        return new LambdaResponse(200, MESSAGE);
    }
}

