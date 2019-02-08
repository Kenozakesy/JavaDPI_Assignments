package applicationforms.loanbroker.loanbroker;

import mix.messaging.requestreply.RequestReply;
import mix.model.bank.*;
import mix.model.loan.LoanRequest;

import javax.annotation.PostConstruct;
import javax.swing.*;

public class Broker {

    private DefaultListModel<JListLine> listModel = new DefaultListModel<JListLine>();
    public JList<JListLine> list;

    public Broker()
    {
        list = new JList<JListLine>(listModel);
    }

    private JListLine getRequestReply(LoanRequest request){
        for (int i = 0; i < listModel.getSize(); i++){
            JListLine rr =listModel.get(i);
            if (rr.getLoanRequest().equals(request)){
                return rr;
            }
        } return null;
    }

    public JListLine getRequestReply(BankInterestRequest request){
        System.out.println("request" + request.getAmount());
        for (int i = 0; i < listModel.getSize(); i++){
            JListLine rr =listModel.get(i);
            if (rr.getBankRequest().equals(request)){
                return rr;
            }
        }
        System.out.println("NULL returned");
        return null;

    }

    public BankInterestRequest add(LoanRequest loanRequest){

        //UI
        BankInterestRequest bankInterestRequest = new BankInterestRequest(loanRequest.getAmount(), loanRequest.getTime());
        listModel.addElement(new JListLine(loanRequest, bankInterestRequest));

        list.repaint();
        //UI
        //add(loanRequest, bankInterestRequest);
        return bankInterestRequest;
    }

    //UI
    public void add(BankInterestRequest request, BankInterestReply bankReply){
        JListLine rr = getRequestReply(request);
        if (rr!= null && bankReply != null){
            rr.setBankReply(bankReply);;
            list.repaint();
        }
    }



    //endregion
}
