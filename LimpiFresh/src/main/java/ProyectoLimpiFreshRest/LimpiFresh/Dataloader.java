package ProyectoLimpiFreshRest.LimpiFresh;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Producto;
import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Rol;
import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Usuario;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.ProductoRepository;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.RolRepository;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.UsuarioRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class Dataloader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final Faker faker;

    public Dataloader(RolRepository rolRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        // Configuramos Faker en español para que los datos sean coherentes con tu app
        this.faker = new Faker(new Locale("es"));
    }

    @Override
    public void run(String... args) throws Exception {
        // Ejecutamos en orden para respetar las relaciones de la BD
        crearRoles();
        crearUsuarios();
        crearProductos();
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

    private void crearProductos() {
        if (productoRepository.count() == 0) {
            System.out.println("🔄 Generando catálogo de productos...");
            
            // Lista de categorías para rotar
            List<String> categorias = Arrays.asList("Cocina", "Baño", "Ropa", "Pisos", "Accesorios", "Multiuso");
            
            for (int i = 0; i < 20; i++) {
                Producto p = new Producto();
                
                // Generamos datos realistas de limpieza
                String material = faker.commerce().material();
                String nombreProducto = "Limpiador " + material + " " + faker.commerce().productName();
                
                p.setNombre(nombreProducto);
                p.setDescripcionCorta(faker.lorem().sentence(5));
                p.setDescripcionLarga(faker.lorem().paragraph(2));
                
                // Precios entre 1000 y 15000
                int precio = faker.number().numberBetween(1000, 15000);
                p.setPrecio(precio);
                
                // Lógica de ofertas
                boolean esOferta = i % 3 == 0; // Cada 3 productos, uno es oferta
                p.setOferta(esOferta);
                if (esOferta) {
                    p.setPrecioOferta((int) (precio * 0.8)); // 20% descuento
                } else {
                    p.setPrecioOferta(null);
                }

                p.setStock(faker.number().numberBetween(0, 100));
                p.setCategoria(categorias.get(faker.number().numberBetween(0, categorias.size())));
                
                // Usamos una imagen genérica o nombre de archivo simulado
                // Si usas la estrategia de "nombre de archivo", esto simula lo que guardarías
                p.setImg("producto_" + (i + 1) + ".jpg"); 
                
                p.setIva(19);

                productoRepository.save(p);
            }
            System.out.println("20 Productos generados exitosamente.");
        }
    }
}