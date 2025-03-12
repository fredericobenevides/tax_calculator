# Tax Calculator

This program calculate the profit and loss between the trades and output the tax for each trade.

## Building the project

The project was built using the gradle. To build and generate the jar execute the following command in the root of the project:

```
./gradlew clean build
```

The project will build the jar file `tax_calculate.jar` located in the following directory: `tax_calculator/build/libs/tax_calculator.jar`

After the project is built, you can simply run:

```
java -jar build/libs/tax_calculator.jar 
```

or if you prefer to use a file as input, you can use the following command

```
java -jar build/libs/tax_calculator.jar < examples/case1.txt
```

## Usage

The program expect to receive a json list as input. The object data should be a list containing the following attributes
:


| Field     | Description                                                 |
|-----------|-------------------------------------------------------------|
| operation | If the operation is `buy` or `sell`.                        |
| unit-cost | The unit price of the trade. The price is a decimal number. |
| quantity  | How many stock market were negotiated.                      |

This is an example of the input:

```
[{"operation":"buy", "unit-cost":10.00, "quantity": 100},{"operation":"sell", "unit-cost":15.00, "quantity": 50},{"operation":"sell", "unit-cost":15.00, "quantity": 50}]
``` 

The output will return a json list containing the field tax for each trade operation.

| Field | Description                             |
|-------|-----------------------------------------|
| tax   | Tax payment in a single trade operation |

This is an example of the output:

```
[{"tax":0.0},{"tax":0.0},{"tax":0.0}]
```

When running the application, the program allows to receive as many list input as you want. The program will only calculate all the loaded
data after a blank line is inserted.

You can check all the examples in the folder `examples`

## Running automated tests

The application is built with gradle using JUnit. You can use the following command to run all the tests in the application:

```
./gradlew test
```

## External libraries

The application was developed using the following third libraries: Gson and JUnit.

* Gson - A library to allow to parse and generate json data.
* JUnit - A library to allow the creation of tests

## Design and Implementation

The code was first implemented using a big loop to allow me to test the input and output to check if the calculation was working correctly.
The code got so complex since there were many conditions to calculate or not the tax.

To simplify the code, I worked with some refactoring to move some Business Rules to a specific service, like calculating only the profit,
and tax. 

I thought to use some design pattern like Chain Of Responsibility, State and so on. Since there were requirements to not use interface, I 
decided to avoid them.

I keep working the code, refactoring and simplifying. I wasn't so much happy because even is simple some part, there are complexities managing
the calculation of the profit, deducting the loss. So after analyzing the code I came up with an idea. 

I decided to implement the code using the Domain Specific Language (`DSL`). The advantages of the DSL is to allow a quick understanding of the code.
Even the implementation of the DSL still took some time to achieve this goal, needed some refactoring, the advantages I got after I finished was great. 
With the new updated code, the code got clear for what its doing. I was able to create many small code to have a Single of Responsibility which also 
allow me to have a better unit tests.  
 
There are some disadvantages for sure, depending on the changes of the requirement, the DSL might be updated to change the structure instead of adding
just a single updated point. The good point with this design, this allows us to have the root starter point to keep the code simpler and easier for anyone 
to understand and manage the code. 

**Note:** currently the designed DSL use a property called `processOperation`. This field is a requirement to allow the code to decide which feature inside 
the chain must be executed or not. The DSL is not lazy so this field allow to decide if a chain in the buyOperation will be executed or in the sellOperation.
This field is `initialized` during the creation of the `isBuyOperation` or `isSellOperation`.