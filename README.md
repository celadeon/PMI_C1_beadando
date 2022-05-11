# Kisbolti nyilvántartó program

A program XML fájlban tárolja a nyilvántartási listát, amit indításkor automatikusan betölt és bezáráskor elment.  

## Mappák felépítése

A program két mappát tartalmaz:

- `src`: forráskódot tartalmazó mappa.
- `resources`: a nyivántartást tartalmazó XML fájl (products.xml) található itt.

## Osztályok
### Product: 

Privát attributumok:
- `Name: String`: a termék neve
- `Id: int`: a termék azonosítója
- `Price: float`: a termék ára
- `Amount: int`: a termék raktáron lévő mennyisége

Publikus metódusok:
- `Product(String name, int id, float price, int amount)`: paraméteres konstruktor
- `Getterek, setterek (name, id, price, amount)`
- `Comparator (name, id, price, amount)`: a nyilvántartási lista rendezéséhez szükséges comparatorok minden attributumhoz
- `printProduct(): void`: formázva kiírja a konzolra a termék attributumait


### Store

Privát attributumok:
- `productList: ArrayList<Product>`: a termékeket tartalmazó lista

Publikus metódusok:
- `Store()`: üres konstruktor
- `addProduct(Product product): void`: paraméterben megadott termék hozzáadása a listához
- `removeProduct(int index): void`: paraméterben megadott indexű elem törlése a listából
- `printProducts(): void`: konzolra kiírja a termékek listáját `printProduct()` metódussal, vagy azt, hogy üres a lista ha nincs benne egy termék sem
- `getProductIndexById(int id): int`: visszaadja a paraméterben megadott azonosítóval rendelkező termék indexét a listában, vagy -1 -et ha nincs a termék a listában 
- `getProductByIndex(int productIndex): Product`: visszaadja a paraméterben megadott indexű terméket
- `saveProductListToXml(String filePath): void`: elmenti a jelenlegi termék listát a paraméterben megadott XML fájlba
- `readProductListFromXml(String filePath): void`: betölti a paraméterben megadott XML fájlból a terméklistát a `productList`-be
- `sortList(int sortType): void`: a paraméterben megadott módon rendezi a listát
  - `0`: név szerint
  - `1`: azonosító szerint
  - `2`: ár szerint
  - `3`: mennyiség szerint

Privát metódusok:
- `createChildElement(Document document, Element parent, String tagName, String value): void`: segédmetódus az XML-be íráshoz


### App

Ez az osztály tartalmazza a `main`-t és egy példányt a `Store` osztályból.

Main:
- Konzolra kiírja a termékek listáját
- Lehetővé teszi a termékek és lista kezelését almenü kiválaszásával
  - `ADD[1]`: új termék hozzáadása a listához
  - `REMOVE[2]`: termék törlése a listából
  - `MODIFY[3]`: termék adatainak módosítása
  - `SORT[4]`: lista rendezése
  - `EXPORT[5]`: jelenlegi nyilvántartási lista lementése tetszőleges XML fájlba
  - `IMPORT[6]`: nyilvántartás lista betöltése tetszőleges XML fájlból
  - `EXIT[0]`: program bezárása
- A program indításakor a `resources/products.xml`-böl betölti a nyilvántartást és a program bezárásakor ugyanebbe a fájlba menti

Publikus metódusok:
- `menuAdd(): void`: almenü új termék felvételéhez a listába, `createNewProduct()` metódus segítségével 
- `menuRemove(): void`: almenü termék törléséhez, bekéri a felhasználótól törölni kívánt termék azonosítóját majd ha az létezik a listában törli
- `menuModify(): void`: almenü termék módosításához, bekér a felhasználótól egy azonosítót majd egy újabb almenü segítségével kiválaszthatjuk, hogy a termék melyik adatát szeretnénk megváltoztatni 
- `menuSort(): void`: almenü a lista rendezéséhez, megkérdezi a felhasználót, hogy mi szerint szeretné rendezni a listát majd rendezi azt
- `menuExport(): void`: almenü a jelenlegi nyilvántartási lista exportálására, bekér a felhasználótól egy elérési útvonalat és oda menti a listát
- `menuImport(): void`: almenü lista betöltéséhez, bekéri a felhasználótól a betölteni kívánt lista elérési útvonalát majd a jelenlegi listát felülírva betölti

Privát metódusok:
- `createNewProduct(): Product`: egymás után bekéri a felhasználótól az új termék nevét, azonosítóját, árát, és mennyiségét majd visszatér a megadott adatokból létrehozott termékkel


## XML felépítése
- `<products>`: root element
- `<product>`: termék példány tag-je
- `<name>`: adott termék nevét tartalmazó tag 
- `<id>`: adott termék azonosítóját tartalmazó tag 
- `<price>`: adott termék árát tartalmazó tag 
- `<amount>`: adott termék mennyiségét tartalmazó tag
 
Példa:
 
 
    <products>
        <product>
          <name>bread</name>
          <id>1</id>
          <price>1.29</price>
          <amount>23</amount>
        </product>
    </products>
 
