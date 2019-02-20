package applicationforms.loanbroker.loanbroker.Aggregator;

import mix.model.bank.BankInterestRequest;

/**
 * Created by Gebruiker on 20-2-2019.
 */
public class BankCheckSending {

    private boolean ING;
    private boolean Rabobank;
    private boolean ABNAmro;

    private int numberToSend;

    public BankCheckSending()
    {
        ING = false;
        Rabobank = false;
        ABNAmro = false;
        numberToSend = 0;
    }

    public boolean isING() {
        return ING;
    }
    public boolean isRabobank() {
        return Rabobank;
    }
    public boolean isABNAmro() {
        return ABNAmro;
    }
    public int getNumberToSend() {
        return numberToSend;
    }


    public void CheckSendAllowed(BankInterestRequest request)
    {
//        private boolean ING;
        if(request.getAmount() <= 100000 && request.getTime() <= 10)
        {
            ING = true;
            numberToSend +=1;
        }
//        private boolean Rabobank;
        if(request.getAmount() <= 250000 && request.getTime() <= 15)
        {
            Rabobank = true;
            numberToSend +=1;
        }
//        private boolean ABNAmro;
        if((request.getAmount() >= 200000 && request.getAmount() <= 300000) && request.getTime() <= 20)
        {
            ABNAmro = true;
            numberToSend +=1;
        }
    }
}

