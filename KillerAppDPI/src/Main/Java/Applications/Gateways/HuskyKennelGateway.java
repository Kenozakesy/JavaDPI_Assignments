package Applications.Gateways;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import mix.ChainCode.Recievers.MessageRecieverGateway;
import mix.ChainCode.Recievers.MessageSenderGateway;
import mix.ChainCode.Serializers.HuskySerializer;
import mix.messaging.requestreply.RequestReply;
import mix.model.Husky;
import mix.model.loan.LoanReply;
import mix.model.loan.LoanRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class HuskyKennelGateway {

    private MessageRecieverGateway receiver;
    private MessageSenderGateway sender;
    private HuskySerializer serializer;

    public HuskyKennelGateway()
    {
        sender = new MessageSenderGateway("HuskyTrainRequest");
        receiver = new MessageRecieverGateway("TrainedHuskyRequest");
        serializer = new HuskySerializer();

//        MessageListener listener = new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                try {
//                    //convert to message reply
//                    java.lang.reflect.Type type = new TypeToken<RequestReply<LoanRequest, LoanReply>>(){}.getType();
//                    RequestReply<LoanRequest, LoanReply> requestReply = new Gson().fromJson(((TextMessage) message).getText(), type);
//                    LoanRequest loanRequest = requestReply.getRequest();
//                    LoanReply loanReply = requestReply.getReply();
//
//                    //say that message arrived
//                    onLoanReplyArrived(loanRequest, loanReply);
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        receiver.setListener(listener);
    }

    public void sendHuskyToKennel(Husky husky)
    {
        String object = serializer.requestToString(husky);
        Message msg = sender.createTextmessage(object);
        sender.sendMessage(msg);
    }

    //how the fuck to receive back request and reply and give to GUI
    public void onLoanReplyArrived(LoanRequest request, LoanReply reply)
    {
//        for (int i = 0; i < frame.listModel.getSize(); i++){
//            RequestReply<LoanRequest, LoanReply> rr = frame.listModel.get(i);
//            if (rr.getRequest().equals(request)){
//                rr.setReply(reply);
//                break;
//            }
//        }
//        frame.requestReplyList.repaint();
    }
}