package br.com.anacarriel.controller;

import br.com.anacarriel.converters.NumberConverter;
import br.com.anacarriel.exception.UnsuportedMathOperationException;
import br.com.anacarriel.math.SimpleMath;
import org.springframework.web.bind.annotation.*;


@RestController
public class MathController {

    private final SimpleMath math = new SimpleMath();

    @RequestMapping(value = "sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum (@PathVariable(value = "numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {

        if (NumberConverter.isNumeric(numberOne) && NumberConverter.isNumeric(numberTwo)) {
            return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }
        throw new UnsuportedMathOperationException("Please, set a numeric value!");
    }

    @RequestMapping(value = "subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sub (@PathVariable(value = "numberOne") String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception{
        if (NumberConverter.isNumeric(numberOne) && NumberConverter.isNumeric(numberTwo)) {
            return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }
        throw new UnsuportedMathOperationException("Please, set a numeric value!");

    }

    @RequestMapping(value = "multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multi (@PathVariable(value = "numberOne") String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception{
        if (NumberConverter.isNumeric(numberOne) && NumberConverter.isNumeric(numberTwo)) {
            return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }
        throw new UnsuportedMathOperationException("Please, set a numeric value!");

    }

    @RequestMapping(value = "division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double div (@PathVariable(value = "numberOne") String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception{
        if (NumberConverter.isNumeric(numberOne) && NumberConverter.isNumeric(numberTwo)) {
            return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
        }
        throw new UnsuportedMathOperationException("Please, set a numeric value!");

    }

    @RequestMapping(value = "mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media (@PathVariable(value = "numberOne") String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception{
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw new UnsuportedMathOperationException("Please, set a numeric value!");

        return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "squereRoot/{numberOne}", method = RequestMethod.GET)
    public Double raiz_quadrada (@PathVariable(value = "numberOne") String numberOne) throws Exception{
        if (NumberConverter.isNumeric(numberOne)) {
            return math.squereRoot(NumberConverter.convertToDouble(numberOne));
        }
        throw new UnsuportedMathOperationException("Please, set a numeric value!");

    }


}
