const app= Vue.createApp({
    data(){
        return{
            busqueda:"",
            existeProd: false,
            productos:[],
            carrito: [],

        }
    },
    created(){
        this.loadData()
    },
    methods:{
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