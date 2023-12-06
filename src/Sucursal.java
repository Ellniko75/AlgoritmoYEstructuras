import java.security.Key;
import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class Sucursal {

    private static int nextId = 1;
    private int id;
    private String nombre;
    private String localizacion;
    private Arbol cabeza;

    private static ArrayList<Sucursal> ListaSucursales = new ArrayList<>();

    public static void ShowMenu(){
        boolean salir = false;
            while (!salir) {
                System.out.println("MENU PRINCIPAL:");
                System.out.println("1. Ver Sucursales");
                System.out.println("2. Ver Trabajadores");
                System.out.println("3. Agregar Sucursal");
                System.out.println("4. Agregar Trabajador");
                System.out.println("5. Modificar Trabajador");
                System.out.println("6. Ver Trabajadores por seccion");
                System.out.println("0. Salir");

                int num = Sistema.RequestNumber("Eliga una opción: ");
                switch (num) {
                    case 1 -> Mostrar();
                    case 2 -> MostrarTrabajadoresDeUnaSucursal();
                    case 3 -> AgregarSucursal();
                    case 4 -> AgregarUsuario();
                    case 5 -> ModificarUsuario();
                    case 6 -> VerTrabajadoresPorSeccion();
                    case 0 -> salir = true;
                    default -> System.out.println("No es un numero válido");
                }
            }
    }

    private static Usuario crearUsuario(){
        Sistema.Keyboard.nextLine();
        int Pcedula = Sistema.RequestNumber("Ingrese una cedula para el trabajador ");
        Sistema.Keyboard.nextLine();
        String Pnombre = Sistema.RequestString("Ingrese el nombre para el trabajador  ");
        String Papellido = Sistema.RequestString("Ingrese el apellido para el trabajador  ");
        String Ptelefono = Sistema.RequestString("Ingrese un teléfono para el trabajador  ");
        String Pseccion = Sistema.RequestString("Ingrese una sección para el trabajador  ");
        String Pcargo = Sistema.RequestString("Ingrese el cargo para el trabajador  ");
        int Psueldo = Sistema.RequestNumber("Ingrese el sueldo para el trabajador  ");

        return new Usuario(Pcedula,Pnombre,Papellido,Ptelefono,Pseccion,Pcargo,Psueldo);
    }

    private static Usuario crearUsuarioSinCedula(){
        Sistema.Keyboard.nextLine();
        String Pnombre = Sistema.RequestString("Ingrese el nombre para el trabajador  ");
        String Papellido = Sistema.RequestString("Ingrese el apellido para el trabajador  ");
        String Ptelefono = Sistema.RequestString("Ingrese un teléfono para el trabajador  ");
        String Pseccion = Sistema.RequestString("Ingrese una sección para el trabajador  ");
        String Pcargo = Sistema.RequestString("Ingrese el cargo para el trabajador  ");
        int Psueldo = Sistema.RequestNumber("Ingrese el sueldo para el trabajador  ");
        return new Usuario(0,Pnombre,Papellido,Ptelefono,Pseccion,Pcargo,Psueldo);
    }

    private static void AgregarSucursal(){
        Sistema.Keyboard.nextLine();
        String Pnombre = Sistema.RequestString("Ingrese un nombre para la sucursal ");
        String Plocalizacion = Sistema.RequestString("Ingrese una localizacion para la sucursal ");
        Sucursal agregar = new Sucursal(nextId,Pnombre,Plocalizacion);
        ListaSucursales.add(agregar);
        nextId++;
    }

    private static void VerTrabajadoresPorSeccion(){
        //Si no hay Sucursales no se pueden ver usuarios
        if(!Mostrar()){
            return;
        }
        //capturamos el numero de sucursal en la cual el usuario quiere agregar un trabajador
        int numeroSucursal = Sistema.RequestNumber("Eliga la sucursal de la cual quieras ver un trabajador ");
        numeroSucursal=numeroSucursal-1;
        //chequeamos que sea un número válido
        if(numeroSucursal>=ListaSucursales.size() || numeroSucursal<0){
            System.out.println("Esa sucursal no existe");
            return;
        }
        //capturamos la sucursal creamos el usuario y lo agregamos
        Sucursal SucursalSeleccionada = ListaSucursales.get(numeroSucursal);

        Sistema.Keyboard.nextLine();
        String seccion = Sistema.RequestString("Eliga una sección que quiera buscar ");
        Arbol cabezaNULA = new Arbol();
        Arbol cabeza = SucursalSeleccionada.cabeza.BuscarPorSeccion(seccion,cabezaNULA);

        cabeza.Imprimir();


    }

    private static void AgregarUsuario(){
        //Si no hay Sucursales no se puede agregar usuarios
        if(!Mostrar()){
            return;
        }
        //capturamos el numero de sucursal en la cual el usuario quiere agregar un trabajador
        int numeroSucursal = Sistema.RequestNumber("Eliga la sucursal a la que quieres agregarle un trabajador");
        numeroSucursal=numeroSucursal-1;
        //chequeamos que sea un número válido
        if(numeroSucursal>=ListaSucursales.size() || numeroSucursal<0){
            System.out.println("Esa sucursal no existe");
            return;
        }
        //capturamos la sucursal creamos el usuario y lo agregamos
        Sucursal SucursalSeleccionada = ListaSucursales.get(numeroSucursal);

        Usuario usuario = crearUsuario();

        if(SucursalSeleccionada.cabeza==null){
            SucursalSeleccionada.cabeza = new Arbol(usuario);
        }else{
            SucursalSeleccionada.cabeza.Agregar(usuario);
        }
        System.out.println("SE AGREGÓ EL USUARIO");
    }

    private static void MostrarTrabajadoresDeUnaSucursal(){
        //Si no hay Sucursales no se puede mostrar sus trabajadores
        if(!Mostrar()){
            return;
        }
        //capturamos el numero de sucursal en la cual el usuario quiere ver sus trabajadores
        int numeroSucursal = Sistema.RequestNumber("Eliga la sucursal de la cual quiere ver los trabajadores ");
        numeroSucursal=numeroSucursal-1;
        //chequeamos que el numero sea válido
        if(numeroSucursal>=ListaSucursales.size() || numeroSucursal<0){
            System.out.println("Esa sucursal no existe");
            return;
        }
        //capturamos la sucursal
        Sucursal SucursalSeleccionada = ListaSucursales.get(numeroSucursal);
        if(SucursalSeleccionada.cabeza==null){
            System.out.println("La sucursal aún no tiene a ningún trabajador");
            return;
        }
        //imprimimos los trabajadores desde la cabeza
        SucursalSeleccionada.cabeza.Imprimir();
    }

    private static void ModificarUsuario(){
        //Si no hay Sucursales no se puede modficar usuarios
        if(!Mostrar()){
            return;
        }
        //capturamos el numero de sucursal en la cual el usuario quiere ver sus trabajadores
        int numeroSucursal = Sistema.RequestNumber("Eliga la sucursal de la cual quiere modificar un trabajador ");
        numeroSucursal=numeroSucursal-1;
        //chequeamos que el numero sea válido
        if(numeroSucursal>=ListaSucursales.size() || numeroSucursal<0){
            System.out.println("Esa sucursal no existe");
            return;
        }
        //capturamos la sucursal
        Sucursal SucursalSeleccionada = ListaSucursales.get(numeroSucursal);
        if(SucursalSeleccionada.cabeza==null){
            System.out.println("La sucursal aún no tiene a ningún trabajador");
            return;
        }
        Sistema.Keyboard.nextLine();
        //le pedimos al usuario la cédula de la persona que quiere modificar
        int cedulaABuscar = Sistema.RequestNumber("Ingrese la cédula de quien quiere modificar");
        Arbol usuarioAModificar = SucursalSeleccionada.cabeza.Buscar(cedulaABuscar);

        //si no existe tal usuario con tal cédula mostramos una advertencia
        if(usuarioAModificar==null){
            System.out.println("No existe usuario con tal cédula");
            return;
        }
        //modificamos el usuario viejo con el nuevo
        Usuario usuarioNuevo = crearUsuarioSinCedula();
        usuarioAModificar.usuario.setNombre(usuarioNuevo.getNombre());
        usuarioAModificar.usuario.setApellido(usuarioNuevo.getApellido());
        usuarioAModificar.usuario.setCargo(usuarioNuevo.getCargo());
        usuarioAModificar.usuario.setSeccion(usuarioNuevo.getSeccion());
        usuarioAModificar.usuario.setFechaIngreso(usuarioNuevo.getFechaIngreso());
        usuarioAModificar.usuario.setSueldo(usuarioNuevo.getSueldo());
        usuarioAModificar.usuario.setTelefono(usuarioNuevo.getTelefono());

        System.out.println("El usuario se modificó correctamente");

    }

    private static boolean Mostrar(){
        if(ListaSucursales.isEmpty()){
            System.out.println("No hay sucursales cargadas");
            return false;
        }
        for(Sucursal suc : ListaSucursales){
            System.out.println(suc.MostrarSingular());
        }
        return true;
    }
    private String MostrarSingular(){
        String msg = this.id + " "+ this.nombre + " " + this.localizacion;
        return  msg;
    }

    public Sucursal(int Pid, String Pnombre, String Plocalizacion) {
        this.id = Pid;
        this.nombre = Pnombre;
        this.localizacion = Plocalizacion;
        this.cabeza=null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Arbol getCabeza() {
        return cabeza;
    }

    public void setCabeza(Arbol cabeza) {
        this.cabeza = cabeza;
    }
}
