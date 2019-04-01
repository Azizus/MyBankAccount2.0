# MyBankAccount2.0

## Statement
### Bank account kata
Think of your personal bank account experience When in doubt, go for the simplest solution


## Requierements
* Deposit and Withdrawal
* Account statement (date, amount, balance)
* Statement printing

## User Stories 

### U1
In order to save money As a bank client I want to make a deposit in my account
### U2
In order to retrieve some or all of my savings As a bank client I want to make a withdrawal from my account
### U3
In order to check my operations As a bank client I want to see the history (operation, date, amount, balance) of my operations

## Implementation
Pour la réalisation de ce projet nous avons décider d'utiliser le framework Spring avec l'ORM JPA pour la persistance des données.
Nous allons créer deux classe persistantes : ACCOUNT et TRANSACTION
Lors des operations, le montant et le type de la transaction ainsi que le compté concerné doivent etre connu. Ceci permettra a l'application de vérifier le solde existant en cas de retrait et d'informer l'utilisateur en cas d'incapacité de retrait pour solde insuffisant.
Ensuite il suffit d'indiquer le compte duquel on veut imprimer l'historique des transactions.



## Run 
```
git clone https://github.com/Azizus/MyBankAccount2.0.git
mvn test
```
