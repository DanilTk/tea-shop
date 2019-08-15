package service.validation;

import model.Client;
import service.exceptions.ClientException;

public class ClientValidator {

    public static boolean validateClient(Client client) throws ClientException {

        return validateClientIsNotNull(client)
                && validateClientNameIsNotNull(client)
                && validateClientSurnameIsNotNull(client);
    }

    private static boolean validateClientIsNotNull(Client client) {

        return client != null;
    }

    private static boolean validateClientNameIsNotNull(Client client) {

        return client.getName() != null;
    }

    private static boolean validateClientSurnameIsNotNull(Client client) {

        return client.getSurname() != null;
    }
}