package sustech.learn.consult.service.impl;

import sustech.learn.consult.service.CodeService;

public class CodeServiceImpl implements CodeService {
    @Override
    public int sendRegisterMessage(String phoneNumber) {
        return 0;
    }

    @Override
    public boolean checkVrfCode(String phoneNumber, int VrfCode) {
        return false;
    }
}
