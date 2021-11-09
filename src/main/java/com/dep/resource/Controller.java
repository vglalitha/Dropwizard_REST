package com.dep.resource;

import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class Controller {

    @GET
    @Path("/getTweets")
    public static ArrayList<String> Gettweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        ArrayList<String> arrayList = new ArrayList<String>();
        List<Status> status = twitter.getHomeTimeline();
        for (Status st : status) {
            arrayList.add(st.getText());
        }
        return arrayList;
    }


    @GET
    @Path("/healthCheck")
    public String healthCheck() {

        return "Rececived ping " + new Date();
    }

    @POST
    @Path("/tweetAgain")
    public Response tweetAgain(Request request) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        String post = request.getMessage();
        if (StringUtil.isEmpty(post)) {
            return Response.status(400, "Please enter valid tweet").build();
        } else {
                Status status =twitter.updateStatus(post);
                if(status.getText().equals(post)){
                    return Response.status(200, "Request is successful").build();
            } else{
                    return Response.status(500, "internal server error").build();
            }
        }

    }
}