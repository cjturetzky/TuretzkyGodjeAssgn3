package com.godjieturetzky;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.paho.client.mqttv3.*;

public class MQTTPub implements Runnable, PropertyChangeListener {
    private String broker = "tcp://test.mosquitto.org:1883";
    private String topic = "CalPoly/CSC509/brokerverse/" + Main.IDNAME;
    private String clientId = "GodjieTuretzky-Publisher";
    private MqttClient client;
     
    public void run(){

        try{
            // Create new Client and connect it to MQTT Server
            client = new MqttClient(broker, clientId);
            client.connect();
            System.out.println("Connected to broker: " + broker);
            Blackboard.getInstance().addPropertyChangeListener(this);

            //Keep the Publisher alive while Blackboard is running
            while(Blackboard.getInstance().isRunning){}
            client.close();
        } catch (MqttException e){
            e.printStackTrace();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Only publish a message to the MQTT server when player position has updated
        // This avoids flooding the server with redundant messages
        
        // Create the content string using the Player Position stored in the Blackboard
                Square player = Blackboard.getInstance().getSquares().get(0);
                String content = Main.IDNAME + "," + String.valueOf(player.getX()) + "," + String.valueOf(player.getY() + ",256,0,0");

                // MQTT only accepts bytes, so convert the message into bytes
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(2); // Qos 2 means ensure the message is sent exactly once
                if (client.isConnected())
                    try {
                        client.publish(topic, message);
                    } catch (MqttPersistenceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } // Only publish if the client successfully connected
    }
}
