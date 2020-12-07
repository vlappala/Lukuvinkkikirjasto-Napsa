Feature: kayttaja voi hakea lukuvinkin otsikon perusteella

    Scenario: kun kayttaja hakee olemassaolevaa lukuvinkkia sen koko nimella, lukuvinkki loytyy
        Given on olemassa lukuvinkki nimelta "Effective Java"
        When kayttaja hakee lukuvinkkia hakusanalla "Effective Java"
        Then lukuvinkki "Effective Java" naytetaan konsolissa

    Scenario: kun kayttaja hakee olemassaolevaa lukuvinkkia osalla sen nimesta, lukuvinkki loytyy
        Given on olemassa lukuvinkki nimelta "Effective Java"
        When kayttaja hakee lukuvinkkia hakusanalla "Eff"
        Then lukuvinkki "Effective Java" naytetaan konsolissa

    Scenario: haulla loytyvat kaikki lukuvinkit, joiden otsikossa on haettu merkkijono
        Given on olemassa lukuvinkit nimelta "Effective Java" ja "Core Java 2"
        When kayttaja hakee lukuvinkkia hakusanalla "Java"
        Then molemmat lukuvinkit "Effective Java" ja "Core Java 2" naytetaan konsolissa

    Scenario: kun kayttaja hakee olematonta lukuvinkkia, lukuvinkkia ei loydy
        Given ei ole olemassa lukuvinkkia nimelta "Core Java 2"
        When kayttaja hakee lukuvinkkia hakusanalla "Core Java 2"
        Then lukuvinkkia "Core Java 2" ei loydy

