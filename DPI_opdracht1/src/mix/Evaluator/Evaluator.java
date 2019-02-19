package mix.Evaluator;
import mix.model.loan.LoanRequest;

//this is a hardcoded check class should later be removed
public class Evaluator {

    public boolean ING;
    public boolean ABNAmro;
    public boolean Rabobank;
    public int amountSend;

    public Evaluator()
    {
        ING = false;
        ABNAmro = false;
        Rabobank = false;
        amountSend = 0;
    }

    public void checkSend(LoanRequest request)
    {
        //ING <=100.000 <=10
        if(request.getAmount() <= 100000 && request.getTime() <= 10)
        {
            ING = true;
            amountSend += 1;
        }
        //ABN 200.00 - 300.000 <=20
        if((request.getAmount() >= 200000 && request.getAmount() <= 300000) && request.getTime() <= 20)
        {
            ING = true;
            amountSend += 1;
        }
        //Rabobank <=250.000 <=15
        if(request.getAmount() <= 250000 && request.getTime() <= 15)
        {
            ING = true;
            amountSend += 1;
        }
    }

}
