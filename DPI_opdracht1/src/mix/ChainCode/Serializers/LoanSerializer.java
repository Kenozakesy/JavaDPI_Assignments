package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

public class LoanSerializer {

    private Gson gson;

    public LoanSerializer()
    {
        gson = new Gson();
    }

    //converts loanRequest to string
    public String requestToString(LoanRequest request)
    {
        String object = gson.toJson(request);
        return object;
    }

    //converts string to loanRequest
    public LoanRequest requestFromString(String str)
    {
        LoanRequest request = gson.fromJson((str), LoanRequest.class);
        return request;
    }

    //convert reply to string
    public String replyToString(LoanReply reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    //converts string to loanReply
    public LoanReply replyFromString(String str)
    {
        LoanReply reply = gson.fromJson((str), LoanReply.class);
        return reply;
    }


}
