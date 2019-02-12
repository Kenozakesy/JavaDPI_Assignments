package mix.ChainCode.Serializers;

import com.google.gson.Gson;
import mix.model.bank.BankInterestReply;
import mix.model.bank.BankInterestRequest;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

public class BankSerializer {

    private Gson gson;

    public BankSerializer()
    {
        gson = new Gson();
    }

    //converts loanRequest to string
    public String requestToString(BankInterestRequest request)
    {
        String object = gson.toJson(request);
        return object;
    }

    //converts string to loanRequest
    public BankInterestRequest requestFromString(String str)
    {
        BankInterestRequest request = gson.fromJson((str), BankInterestRequest.class);
        return request;
    }

    //convert reply to string
    public String replyToString(BankInterestReply reply)
    {
        String object = gson.toJson(reply);
        return object;
    }

    //converts string to loanReply
    public BankInterestReply replyFromString(String str)
    {
        BankInterestReply reply = gson.fromJson((str), BankInterestReply.class);
        return reply;
    }


}
