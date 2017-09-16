package com.boot.app.service;

import java.util.concurrent.Future;

import com.boot.app.model.Greeting;

public interface EmailService {
	
	Boolean send(Greeting greeting);
	
	void sendAsync(Greeting greeting);
	
	Future<Boolean> sendAsyncWithResult(Greeting greeting);

}
