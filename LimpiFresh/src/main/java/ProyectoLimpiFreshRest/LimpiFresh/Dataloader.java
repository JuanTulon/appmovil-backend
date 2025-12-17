package ProyectoLimpiFreshRest.LimpiFresh;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Producto;
import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Rol;
import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Usuario;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.ProductoRepository;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.RolRepository;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Dataloader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public Dataloader(RolRepository rolRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Ejecutamos en orden para respetar las relaciones de la BD
        crearRoles();
        crearUsuarios();
        crearProductosIdenticosApp();
    }

    private void crearRoles() {
        if (rolRepository.count() == 0) {
            Rol admin = new Rol();
            admin.setNombreRol("ADMIN");
            
            Rol cliente = new Rol();
            cliente.setNombreRol("CLIENTE"); // El rol por defecto para usuarios nuevos

            rolRepository.saveAll(Arrays.asList(admin, cliente));
            System.out.println("Roles creados correctamente.");
        }
    }

    private void crearUsuarios() {
        if (usuarioRepository.count() == 0) {
            // Buscamos los roles que acabamos de crear
            Rol rolAdmin = rolRepository.findByNombreRol("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Rol ADMIN no encontrado"));
            Rol rolCliente = rolRepository.findByNombreRol("CLIENTE")
                    .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado"));

            // 1. Crear un Administrador Maestro (Para que tú puedas entrar al BackOffice)
            Usuario admin = new Usuario();
            admin.setNombre("Juan Admin");
            admin.setEmail("admin@limpiohogar.cl");
            admin.setPassword("admin123.");
            admin.setRut("12345678-5");
            admin.setRegion("Metropolitana");
            admin.setComuna("Santiago");
            admin.setRol(rolAdmin);

            // 2. Crear un Cliente de prueba
            Usuario cliente = new Usuario();
            cliente.setNombre("Cliente Prueba");
            cliente.setEmail("cliente@correo.com");
            cliente.setPassword("123456");
            cliente.setRut("22.222.222-2");
            cliente.setRegion("Valparaíso");
            cliente.setComuna("Viña del Mar");
            cliente.setRol(rolCliente);

            usuarioRepository.saveAll(Arrays.asList(admin, cliente));
            System.out.println("Usuarios de prueba creados.");
        }
    }

    private void crearProductosIdenticosApp() {
        // Solo cargamos si la base de datos está vacía
        if (productoRepository.count() == 0) {
            System.out.println("🔄 Sincronizando catálogo con App Móvil...");

            // 1. Quix (Cat 1: Cocina)
            guardar("Lavaloza Quix 1L", "Desengrasante con aroma a limón", 
                    "Poder desengrasante superior que rinde más.", 
                    2990, 50, "Cocina", "quix", "Quix", "Botella 1L", null);

            // 2. Cif (Cat 1: Cocina) - TIENE PRECIO ANTERIOR (OFERTA)
            // Lógica: Precio Base 2790, Oferta 2490
            guardar("Cif Crema Limpiador", "Limpiador cremoso multiuso", 
                    "Micropartículas que remueven la suciedad más difícil.", 
                    2790, 40, "Cocina", "cif", "Cif", "Botella 750g", 2490);

            // 3. Clorox (Cat 2: Baño)
            guardar("Clorox Cloro Gel 900ml", "Desinfecta, limpia y blanquea", 
                    "Máxima precisión sin salpicaduras.", 
                    3190, 35, "Baño", "clorox", "Clorox", "Botella 900ml", null);

            // 4. Pato (Cat 2: Baño)
            guardar("Pato Discos Activos", "Gel limpiador adhesivo para inodoro", 
                    "Mantiene el inodoro fresco y limpio en cada descarga.", 
                    4990, 30, "Baño", "pato", "Pato", "Pack 6 discos", null);

            // 5. Ariel (Cat 3: Ropa) - TIENE PRECIO ANTERIOR (OFERTA)
            // Lógica: Precio Base 14990, Oferta 12990
            guardar("Detergente Ariel 3L", "Concentrado para ropa blanca y de color", 
                    "Remueve manchas difíciles a la primera lavada.", 
                    14990, 20, "Ropa", "ariel", "Ariel", "Botella 3L", 12990);

            // 6. Downy (Cat 3: Ropa)
            guardar("Suavizante Downy 1.5L", "Aroma fresco y duradero", 
                    "Protege las fibras y deja un aroma increíble.", 
                    6990, 25, "Ropa", "downy", "Downy", "Botella 1.5L", null);

            // 7. Poett (Cat 4: Pisos)
            guardar("Poett Limpiador Lavanda", "Aromatizante para pisos 1.8L", 
                    "Fragancia de larga duración para todo tu hogar.", 
                    3590, 40, "Pisos", "poett", "Poett", "Botella 1.8L", null);

            // 8. Esponja (Cat 5: Accesorios)
            guardar("Esponja Virutex (Pack 3)", "Esponja multiuso", 
                    "Fibra abrasiva y espuma de alta duración.", 
                    1990, 100, "Accesorios", "esponja", "Virutex", "Pack 3 unidades", null);

            // 9. Paños (Cat 5: Accesorios)
            guardar("Paños de Microfibra", "Paños reutilizables (Pack 5)", 
                    "No dejan pelusas, ideales para vidrios y muebles.", 
                    4990, 60, "Accesorios", "panos", "Genérica", "Pack 5 unidades", null);

            // 10. Lysol (Cat 6: Multiuso/Desinfección)
            guardar("Lysol Spray Desinfectante", "Elimina el 99.9% de gérmenes", 
                    "Desinfección efectiva en superficies duras y blandas.", 
                    5990, 30, "Multiuso", "lysol", "Lysol", "Aerosol 340g", null);

            System.out.println(" 10 Productos idénticos a la App cargados.");
        }
    }

    private void guardar(String nombre, String corta, String larga, int precioBase, int stock, 
                         String categoria, String img, String marca, String formato, Integer precioOferta) {
        
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcionCorta(corta);
        p.setDescripcionLarga(larga); // Usamos una descripción un poco más larga generada
        p.setPrecio(precioBase);
        p.setStock(stock);
        p.setCategoria(categoria);
        
        // Aquí guardamos la clave ("cif", "quix") para que Android use el recurso local
        p.setImg(img); 
        
        p.setIva(19);
        p.setMarca(marca);   // Asumiendo que agregaste este campo a tu modelo backend
        p.setFormato(formato); // Asumiendo que agregaste este campo

        // Lógica de Ofertas
        if (precioOferta != null) {
            p.setOferta(true);
            p.setPrecioOferta(precioOferta);
        } else {
            p.setOferta(false);
            p.setPrecioOferta(null);
        }

        productoRepository.save(p);
    }
}