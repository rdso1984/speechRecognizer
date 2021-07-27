package calculatorspeech;

public class MakeDecision {
    
    double number1, number2;
    double lastResult;
    boolean twoNumbers = false;
    boolean operation = false;
    int option;
    
    //Define number1, number2, option
    public void stringToNumber(String text){
        int quantLoop = 1;
        double total = 0;
        this.number1 = 0;
        this.number2 = 0;
        
        String[] words = text.split(" "); //Split the words
        kindOfOperation(words[0]); //Define which operation will be calculate
        
        for(int i=0; i<words.length; i++){ //After delete this comand
            System.out.println("Position " + i + ":" + words[i]); 
        }
        System.out.println();
        
        if(operation == true){
            for(int i=(words.length)-1; i>=1; i--){
                if("one".equals(words[i])){
                    total = total + (1*quantLoop);
                    quantLoop *= 10;
                    continue;
                }
                if("two".equals(words[i])){
                    total = total+(2*quantLoop);
                    quantLoop *= 10;
                    continue;
                }
                if("three".equals(words[i])){
                    total +=(3*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                if("four".equals(words[i])){
                    total +=(4*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                if("five".equals(words[i])){
                    total +=(5*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                if("six".equals(words[i])){
                    total +=(6*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                if("seven".equals(words[i])){
                    total +=(7*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                if("eight".equals(words[i])){
                    total +=(8*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                if("nine".equals(words[i])){
                    total +=(9*quantLoop);
                    quantLoop *=10;
                    continue;
                }
                
                /*if("point".equals(words[i])){ //ARRUMAR OS NÚMEROS APÓS O PONTO
                    total +=0.0;
                    continue;
                }*/
                
                if("and".equals(words[i])){
                    this.number2 = total;
                    total = 0;
                    quantLoop = 1;
                }
            }
            this.number1 = total;
            System.out.println("O valor do NUMBER1 é: "+ this.number1);
            System.out.println("Valor do NUMBER2 é: " + this.number2);
            System.out.println();
        }
        else{
            System.out.println("Não entendi a operação a realizar.");
            System.out.println("Por gentileza, repita a operação.");
        }
    }
        
    private void kindOfOperation(String text){
        
        if("plus".equals(text)){
            this.option = 1;
            this.operation = true;
            System.out.println("Foi escolhido PLUS");
        }
        else{
            if("minus".equals(text)){
            this.option = 2;
            this.operation = true;
            System.out.println("Foi escolhido MINUS");
            }
            else{
                if("multiply".equals(text)){
                    this.option = 3;
                    this.operation = true;
                    System.out.println("Foi escolhido MULTIPLY");
                }
                else{
                    if("division".equals(text)){
                        this.option = 4;
                        this.operation = true;
                        System.out.println("Foi escolhido DIVISION");
                    }
                    else{
                        this.operation=false;
                    }
                }
            }   
        }
    }
    
    protected double getNumber1(){
        return number1;
    }
    
    protected double getNumber2(){
        return number2;
    }
    
    protected int getOption(){
        return option;
    }
        
    //Define resultFinal        
    protected double lastResult(int option, double number1, double number2){
        
        if(number2==0){
            number2 = number1;
            number1 = this.lastResult;
            
            switch(option){
                case 1:
                    this.lastResult = number1+number2;
                    break;
                
                case 2:
                    this.lastResult = number1-number2;
                    break;
                
                case 3:
                    this.lastResult = number1*number2;
                    break;
                
                case 4: 
                    this.lastResult = number1/number2;
                    break;
            }
        }
        else{
            switch(option){
                case 1:
                    this.lastResult = number1+number2;
                    break;
                
                case 2: 
                    this.lastResult = number1-number2;
                    break;
                
                case 3:
                    this.lastResult = number1*number2;
                    break;
                
                case 4: 
                    this.lastResult = number1/number2;
                    break;
            }
        }
        return this.lastResult;   
    }
    
}