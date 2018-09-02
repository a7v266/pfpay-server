package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionControllerImpl implements TransactionController {

    private static final String SIGNATURE_ALGORITHM = "SHA1withDSA";
    private static final String SIGNATURE_PROVIDER = "SUN";


    /*
    @Override
    public Confirmation confirmTransaction(Transaction transaction) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        Confirmation confirmation = new Confirmation();

        String hashCode = null; //DigestUtils.createSHA256(StringJoiner.on().join(transactionService.getLastHashCode(), transaction.getTransactionData()));

        if (ObjectUtils.notEquals(hashCode, transaction.getHashCode())) {

            transaction.setTransactionStatus(TransactionStatus.CANCELED);

            transactionService.saveTransaction(transaction);

            return null;
        }

        // TODO проверка бизнес условий и подписей


        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM, SIGNATURE_PROVIDER);

        signature.initSign(configService.getPrivateKey());

        signature.update(transaction.getTransactionBytes());

        confirmation.setSignature(signature.sign());

        transaction.addConfirmation(confirmation);

        transaction.setTransactionStatus(TransactionStatus.CREATED);

        transactionService.saveTransaction(transaction);

        return confirmation;
    }
    */
}
