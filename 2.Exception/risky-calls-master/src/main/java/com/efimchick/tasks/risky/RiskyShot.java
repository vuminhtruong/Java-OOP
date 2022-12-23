package com.efimchick.tasks.risky;

import com.efimchick.tasks.risky.util.RussianRoulette;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RiskyShot {

    final int input;
    final RussianRoulette roulette;


    public RiskyShot(final int input,
                     final RussianRoulette roulette) {
        this.input = input;
        this.roulette = roulette;
    }
    private void aIllegalArgumentException(Exception ex) throws FileNotFoundException, IOException {
        if(ex instanceof FileNotFoundException){
            throw new FileNotFoundException("1");
        } else if(ex instanceof IOException){
            throw new IOException("2");
        }
    }
    private void aArithmethicandNumberFormatException(Exception ex) throws NumberFormatException,ArithmeticException{
        if(ex instanceof ArithmeticException){
            throw new ArithmeticException("3");
        } else if(ex instanceof NumberFormatException){
            throw new NumberFormatException("4");
        }
    }

    private void aUnsupportedOperationException(Exception ex) throws UnsupportedOperationException{
        if(ex instanceof UnsupportedOperationException){
            throw new UnsupportedOperationException("5");
        }
    }

    private void aRuntimeException(Exception ex) throws RuntimeException{
        if(ex instanceof RuntimeException){
            throw new RuntimeException();
        }
    }


    public void handleShot() /*You may not add "throws" here*/ {
        // handle method call
        //roulette.shot(input);
        try{
            roulette.shot(input);
        }
        catch (Exception e){
            try{
                aIllegalArgumentException(e);
            }
            catch (FileNotFoundException e1){
                throw new IllegalArgumentException("File is missing",e1);
            }
            catch (IOException e1){
                throw new IllegalArgumentException("File error",e1);
            }
            try{
                aArithmethicandNumberFormatException(e);
            }
            catch (ArithmeticException e1){
                try{
                    roulette.shot(input);
                }
                catch (Exception e2){
                    try {
                        roulette.shot(input);
                    }
                    catch (Exception e3){
                        return;
                    }
                }
            }
            catch(NumberFormatException e1){
                try{
                    roulette.shot(input);
                }
                catch (Exception e2){
                    return;
                }
            }
            try{
                aUnsupportedOperationException(e);
            }
            catch (UnsupportedOperationException e1){
                throw new UnsupportedOperationException("5");
            }
            try{
                aRuntimeException(e);
            }
            catch (RuntimeException e1){
                throw new RuntimeException(e);
            }
        }
    }
}
