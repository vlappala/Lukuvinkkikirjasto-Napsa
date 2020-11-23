Feature: Kayttaja voi lisata lukuvinkin otsikolla

    Scenario: kayttaja voi lisata lukuvinkin kelvollisella otsikolla
        Given konsoli pyytaa lukuvinkin otsikkoa
        When kelvollinen otsikko "Testiotsikko" syotetaan konsoliin
        Then konsoli vastaa viestilla "Luotiin lukuvinkki: Testiotsikko"
