Feature: kayttaja voi poistaa lukuvinkin

    Scenario: kayttaja voi poistaa olemassa olevan lukuvinkin
        Given on olemassa lukuvinkki nimelta "Java Threads"
        When kayttaja poistaa lukuvinkin "Java Threads" poistokomennolla
        Then lukuvinkki "Java Threads" poistetaan