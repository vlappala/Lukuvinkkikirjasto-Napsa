Feature: Kayttaja voi lisata lukuvinkin otsikolla

    Scenario: kayttaja voi lisata lukuvinkin kelvollisella otsikolla
        Given konsoli pyytaa lukuvinkin otsikkoa
        When kelvollinen otsikko "Testiotsikko" syotetaan konsoliin
        Then konsoli vastaa halutulla viestilla

    Scenario: kayttaja ei voi lisata lukuvinkkia tyhjalla otsikolla
        Given konsoli pyytaa lukuvinkin otsikkoa
        When tyhja otsikko syotetaan konsoliin
        Then lukuvinkki ei tallennu tiedostoon
