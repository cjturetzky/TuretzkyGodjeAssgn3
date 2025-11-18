package com.godjieturetzky;
import org.eclipse.paho.client.mqttv3.*;

public class MQTTSub implements Runnable, MqttCallback{
    public void run(){ // The subscriber should run in its own thread so no player inputs are held up
        // MQTT setup
        String broker = "tcp://test.mosquitto.org:1883";
        String topic = "CalPoly/CSC509/brokerverse/#";
        String clientId = "GodjieTuretzky-Subscriber";

        try {
            // Callback setup creates a new subscriber and connects that to the client
            // This client runs in its own thread because it was called with Runnable.run()
			MqttClient client = new MqttClient(broker, clientId);
			client.setCallback(this);
			client.connect();
			System.out.println("Connected to BROKER: " + broker);
			client.subscribe(topic);
			System.out.println("Subscribed to TOPIC: " + topic);
            while(Blackboard.getInstance().isRunning){}
            client.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
    }


	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}
	
	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) {
        String message = new String(mqttMessage.getPayload());
		System.out.println("Message arrived. Topic: " + s +
			" Message: " + message);
            // Process incoming message and add it to Blackboard
            // Incoming topic will come in as csc509/multiverse/[USERNAME]
            // Use USERNAME as ID in Blackboard to allow updates to positions
            try{
                String[] splitMessage = message.split(",");
                // Since we are subscribing to the global topic, we may get our own published messages.
                // Ignore our own messages.
                if(!splitMessage[0].equals(Main.IDNAME)){
                    Blackboard.getInstance().updateSquare(splitMessage[0], Integer.parseInt(splitMessage[1]), Integer.parseInt(splitMessage[2]));
                }
            } catch (Exception e){

            }
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}
}
