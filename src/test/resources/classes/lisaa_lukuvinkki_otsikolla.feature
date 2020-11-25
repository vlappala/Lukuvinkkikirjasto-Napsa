Feature: Kayttaja voi lisata lukuvinkin otsikolla

    Scenario: kayttaja voi lisata lukuvinkin kelvollisella otsikolla
        Given konsoli pyytaa lukuvinkin otsikkoa
        When kelvollinen otsikko "Testiotsikko" syotetaan konsoliin
        Then lukuvinkki tallentuu tiedostoon
        And konsoli vastaa viestilla "Luotiin lukuvinkki: Testiotsikko"

    Scenario: kayttaja ei voi lisata lukuvinkkia tyhjalla otsikolla
        Given konsoli pyytaa lukuvinkin otsikkoa
        When tyhja otsikko syotetaan konsoliin
        Then lukuvinkki ei tallennu tiedostoon
