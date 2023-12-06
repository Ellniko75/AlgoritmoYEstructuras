import java.util.ArrayList;

public class Arbol {
        public Usuario usuario;
        public Arbol izq;
        public Arbol der;


        public Arbol(Usuario usuario){
            this.usuario=usuario;
            this.izq=null;
            this.der=null;
        }
    public Arbol(){
        this.usuario = null;
        this.der = null;
        this.izq = null;
    }


    public void Agregar(Usuario usuario){
            if(usuario.getCi()>this.usuario.getCi() && this.der == null){
                this.der = new Arbol(usuario);
            }else if(usuario.getCi()<=this.usuario.getCi() && this.izq==null){
                this.izq = new Arbol(usuario);
            }else if(usuario.getCi()>this.usuario.getCi()){
                this.der.Agregar(usuario);
            }else{
                this.izq.Agregar(usuario);
            }
        }

        public void Imprimir(){
            if(this.izq!=null) this.izq.Imprimir();

            if(this.usuario!=null)System.out.println(this.usuario.ToString());

            if(this.der!=null) this.der.Imprimir();
        }



        public Arbol Buscar(int cedula){
            if(cedula==this.usuario.getCi()){
                return this;
            }else if(cedula > this.usuario.getCi()){
                if(this.der == null){
                    return null;
                }else{
                    return this.der.Buscar(cedula);
                }
            }else{
                if(this.izq==null){
                    return null;
                }else{
                    return this.izq.Buscar(cedula);
                }
            }

        }

        private void AgregarNodoALista(ArrayList<Arbol>lista,Arbol nodo ){
            lista.add(nodo);
        }

        public Arbol BuscarPorSeccion(String seccion,Arbol cabeza){
            if(this.usuario.getSeccion().equalsIgnoreCase(seccion)){
                if(cabeza.usuario == null){
                    cabeza.usuario = this.usuario;
                }else{
                    cabeza.Agregar(this.usuario);
                }
            }
            if(this.izq!=null){
                this.izq.der.BuscarPorSeccion(seccion,cabeza);
            }
            if(this.der!=null){
               this.der.BuscarPorSeccion(seccion,cabeza);
            }
            return cabeza;

        }

    }


