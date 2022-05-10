## Kisbolti nyilvántartó program

A program XML fájlban tárolja a nyilvántartási listát, amit indításkor automatikusan betölt és bezáráskor elment.  

## Mappák felépítése

A program két mappát tartalmaz:

- `src`: forráskódot tartalmazó mappa.
- `resources`: a nyivántartást tartalmazó XML fájl (products.xml) található itt.

## Osztályok
> Product: 

Privát attributumok:
- `Name: String`: a termék neve
- `Id: int`: a termék azonosítója
- `Price: float`: a termék ára
- `Amount: int`: a termék raktáron lévő mennyisége

Publikus metódusok:
- `Getterek, setterek (name, id, price, amount`
- `Comparator (name, id, price, amount)`: a nyilvántartási lista rendezéséhez szükséges comparatorok minden attributumhoz
- `printProduct(): void`: formázva kiírja a konzolra a termék attributumait

> Store

Privát attributumok:
- `productList: ArrayList<Product>`: a termékeket tartalmazó lista

Publikus metódusok:
- `Store()`: üres konstruktor
- `addProduct(Product product)`: paraméterben megadott termék hozzáadása a listához
- `removeProduct(int index)`: paraméterben megadott indexű elem törlése a listából
- 

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
