Feature: kayttaja voi listata lukuvinkit otsikolla

    Scenario: kun on olemassa yksi lukuvinkki, se nakyy lukuvinkkien listassa
        Given on olemassa lukuvinkki nimelta "Learning Java"
        When kayttaja listaa lukuvinkit listauskomennolla
        Then lukuvinkki "Learning Java" naytetaan listassa

    Scenario: kun on olemassa kaksi lukuvinkkia, ne nakyvat lukuvinkkien listassa
        Given on olemassa lukuvinkit nimelta "Learning Java" ja "Mastering Java"
        When kayttaja listaa lukuvinkit listauskomennolla
        Then molemmat lukuvinkit "Learning Java" ja "Mastering Java" naytetaan listassa

    Scenario: kun ei ole olemassa yhtaan lukuvinkkia, lista on tyhja
        Given ei ole olemassa yhtaan lukuvinkkia
        When kayttaja listaa lukuvinkit listauskomennolla
        Then lista on tyhja
