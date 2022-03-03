package sustech.learn.consult.service;

public interface CodeService {

    int sendRegisterMessage(String phoneNumber);

    boolean checkVrfCode(String phoneNumber, int VrfCode);
}
