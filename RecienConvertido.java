import java.util.Random;
public class RecienConvertido extends Vampiro {

    private int inestabilidad = 50;

    RecienConvertido(String n,int f, int v, int h,String c,String clan, String a){
        super(n, f, v, h,c,clan, a);
        this.inestabilidad = generarInestabilidadAleatorio();
    }
    @Override
    public void comer() {
        if (hambre>1) {
            
        
        Random random = new Random();
        hambre = hambre - random.nextInt(99)+1;
        inestabilidad = 10;
            if (hambre<=0 ) {
                hambre = 1;
            }
        }
        else {
            System.out.println(nombre + " no tiene hambre en este momento.");
        }


    }

    @Override
    public void habilidadEspecial() {
        if (inestabilidad<20){
            System.out.println(nombre + " utiliza su habilidad de recién convertido: Velocidad Explosiva (50 puntos de velocidad temporales).");
            System.out.println("Su velocidad antes de es: "+ velocidad);
            velocidad += 50;
            System.out.println("Su velocidad despues de es: "+ velocidad);
            hambre = 100;
            velocidad = velocidad - 50;
        }
        else{
            System.out.println(nombre + " no puede utilizar su habilidad especial ya que su atributo unico (inestabilidad) es de:"+inestabilidad+". \nDebe ser menor a 20 puntos, para que el vampiro pueda usarlo, comiendo baja su inestabilidad");
        }
    }

    public int getInestabilidad() {
        return inestabilidad;
    }

    private int generarInestabilidadAleatorio() {
        Random random = new Random();
        return random.nextInt(99) + 1; 
    }
}