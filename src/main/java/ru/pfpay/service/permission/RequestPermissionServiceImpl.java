package ru.pfpay.service.permission;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.Request;
import ru.pfpay.validation.ValidationUtils;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class RequestPermissionServiceImpl implements RequestPermissionService {

    @Override
    public ErrorCollector checkSaveRequest(Request request) {

        return ValidationUtils.validate(request, new ErrorCollector(Messages.ERROR_SAVE_REQUEST_FORMAT, request));
    }
}
