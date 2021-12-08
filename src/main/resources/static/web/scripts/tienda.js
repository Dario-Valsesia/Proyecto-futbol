const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            busqueda:"",
            existeProd: false,
            productos:[],
            carrito: ['perro'],

        }
    },
    created(){
        this.datosClienteActual()
        this.loadData()
    },
    methods:{
        datosClienteActual(){
            axios.get("/api/cliente/actual")
            .then(response => {
                this.datosCliente = response.data
            })
        },
        signOut(){
            swal({
                text: "¿Estás seguro que quieres cerrar su sesión?",
                icon:"warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/logout")
                    .then(response=>{
                        window.location.replace("../index.html")
                    })
                }
            })
        },
        loadData(){
            axios.get('/api/productos')
            .then(res => {
                this.productos= res.data   
            })
        }
    },
    computed:{
        busquedaProductos() {
            let productosFiltrados = this.productos.filter(producto => producto.nombreProducto.toLowerCase().includes(this.busqueda.toLowerCase()))
            if(productosFiltrados.length == 0){
                this.existeProd = true
            }else {
                this.existeProd= false
                return productosFiltrados
            }
        },
        
    }
});

let mount = app.mount("#app");