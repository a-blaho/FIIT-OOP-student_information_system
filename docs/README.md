# oop-2020-str-18-a-valach-a-blaho
Aplikácia slúži na správu známok. Učitelia vedia žiakom pridávať a mazať známky. V rámci svojho predmetu si učitelia vedia vytvárať kategórie, do ktorých budú známky zapisovať. Žiaci po každom pridaní a odobratí známky dostanú na domovskej stránke upozornenie a vedia si zapísané známky pozrieť.

Zoznam účtov: 
|username|password| type  |
|--------|--------|-------|
|adam |adam|student|
|david |david|student|
|jakub|jakub|student|
|marek|marek|teacher|
|peter|peter|teacher|

## Hlavné kritériá splnené v projekte:
1. dedenie 
   - triedy [Student](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Student.java#L16) a [Teacher](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Teacher.java#L12) dedia z triedy [User](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/User.java#L8)
   - triedy [RegularGrade](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/RegularGrade.java#L6) a [FinalGrade](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/FinalGrade.java#L6) dedia z triedy [Grade](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/Grade.java#L9)

2. polymorfizmus v aspoň dvoch oddelených hierarchiách dedeni
   - funkcia getGrade() v triedach [RegularGrade](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/RegularGrade.java#L22) a [FinalGrade](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/FinalGrade.java#L22) použitá vo funkcii [average()](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Student.java#L78)
   - funkcia getGradesPath() v triedach [Teacher](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Teacher.java#L169) a [Student](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Student.java#L115) použitá vo funkcii [initialize()](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/controller/MenuController.java#L83) v triede MenuController 
3. použitie rozhraní
   - využité v triede [Observable](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/observer/Observable.java#L11) pri implementovaní návrhového vzoru Observer
4. zapúzdrenie
   - privátne atribúty, ku ktorým pristupujem pomocou getterov a setterov
   - napríklad funkcia [getSubject()](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Teacher.java#L91) v triede Teacher
5. agregácia
   - [atribút](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/user/Teacher.java#L14) typu Subject v triede Teacher
6. oddelenie aplikačnej logiky od používateľského rozhrania
   - použitý model MVC
7. kód organizovaný do balíkov
   - balíky model, view, controller a helper

## Ďalšie kritériá splnené v projekte:
- návrhový vzor [Observer](../model/observer)
- ošetrenie mimoriadnych stavov prostredníctvom [vlastných výnimiek](../src/model/exceptions)
- poskytnutie grafického používateľského rozhrania oddelene od aplikačnej logiky (model MVC)
- explicitné použitie [RTTI](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/controller/MenuController.java#L62)
- použitie [implicitnej implementácie metód](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/observer/Observable.java#L18) v rozhraniach
- použitie [serializácie](https://github.com/OOP-FIIT/oop-2020-str-18-a-valach-a-blaho/blob/1028cd2131f856e6e5a4eff29046253b91de0815/src/model/Serialization.java#L10)
