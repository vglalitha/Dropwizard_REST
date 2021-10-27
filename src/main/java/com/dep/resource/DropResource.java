package com.dep.resource;

import com.dep.tweeet;
import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/api/1.0/twitter")
public class DropResource {

    @GET
    @Path("/healthCheck")
    public String healthCheck(){

        return "Ping Received at " + new Date();
    }



    @POST
    @Path("/tweetAgain")
    public Response tweetAgain(String post) throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("0y5BEWvSstdsSzL1Sf73BKjIm")
                .setOAuthConsumerSecret("nJ8D5SNDYbUUdyk3lQipGyZiVrvcob6KO4fHT3yANYNJscnee4")
                .setOAuthAccessToken("1450743367696994308-dBUe5yjC4RsjyVt8cjht2Cmk0V9iS2")
                .setOAuthAccessTokenSecret("NCJQOsxtxl3ynLmwBn9V7kGeUZ8e0aYADAFJWR6DFxR5q");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();


        String ret=null;
        if(StringUtil.isEmpty(post)) {
            return Response.status(400,"Please enter valid tweet").build();
        }
        else{
            twitter.updateStatus(post);
            return Response.status(200,"successfully tweeted").build();
        }

    }

    @GET
    @Path("/tweet")
    public String time () throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("0y5BEWvSstdsSzL1Sf73BKjIm")
                .setOAuthConsumerSecret("nJ8D5SNDYbUUdyk3lQipGyZiVrvcob6KO4fHT3yANYNJscnee4")
                .setOAuthAccessToken("1450743367696994308-dBUe5yjC4RsjyVt8cjht2Cmk0V9iS2")
                .setOAuthAccessTokenSecret("NCJQOsxtxl3ynLmwBn9V7kGeUZ8e0aYADAFJWR6DFxR5q");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> status = twitter.getHomeTimeline();
        for (Status st : status) {
            System.out.println(st.getUser().getName() + "-------" + st.getText());
        }
        return "recieved timeline";
    }
}