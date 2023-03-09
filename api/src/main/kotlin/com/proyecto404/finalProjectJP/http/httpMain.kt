import com.proyecto404.finalProjectJP.core.Core
import com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory.InMemoryUsers
import com.proyecto404.finalProjectJP.http.HttpApplication

fun main () {
    val core = Core(Core.Configuration(InMemoryUsers()))
    val httpConfig = HttpApplication.Configuration(8080, core)
    HttpApplication(httpConfig).start()
}
