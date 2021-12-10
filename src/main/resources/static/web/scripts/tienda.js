const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            /* busqueda */
            busqueda:"",
            existeProd: false,

            productos:[],

            /* carrito */
            carrito: [],

            /* pago carrito */
            numeroDeTarjeta:"",
            titularTarjeta:"",
            vencimiento:"",
            cvc:""

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
        },
        guardarCarrito() {
            const parsed = JSON.stringify(this.carrito);
            localStorage.setItem('carrito', parsed);
        },
        agregarAlCarrito(producto){
            let posicion = this.carrito.findIndex(prod => prod.id == producto.id);
            if (this.carrito.some(prod => prod.id == producto.id)) {
                if (this.carrito[posicion]["stock"] > 0) {
                    this.carrito[posicion]["cantidad"]++;
                    this.carrito[posicion]["stock"]--;
                }else if (this.carrito[posicion].stock == 0) {
                    swal({
                        text: "Agregaste la última unidad de este producto.",
                        icon:"warning",
                        buttons: "ok"
                    })
                }
            } else {
                let productoAComprar = {
                    "id": producto.id,
                    "name": producto.name,
                    "nombreProducto": producto.nombreProducto,
                    "costo": producto.costo,
                    "precio": producto.precio,
                    "stock": producto.stock - 1,
                    "marca": producto.marca,
                    "talle": producto.talle,
                    "urlImg": producto.urlImg,
                    "cantidad": 1
                }
                this.carrito.push(productoAComprar);
            }
            this.guardarCarrito()
        },
        quitarUnidadDelCarrito(producto){
            if (this.carrito.some(prod => prod.id == producto.id)) {
                let posicion = this.carrito.findIndex(prod => prod.id == producto.id);
                if (this.carrito[posicion]["cantidad"] == 1) {
                    this.carrito[posicion]["stock"]++;
                    this.carrito.splice(posicion, 1)
                    this.guardarCarrito()
                   
                } else if(this.carrito[posicion]["cantidad"] > 1){
                    this.carrito[posicion]["cantidad"] = this.carrito[posicion]["cantidad"] - 1;
                    this.carrito[posicion]["stock"]++;
                    this.guardarCarrito()

                }else if (this.carrito[posicion]["cantidad"] == 0) {
                   
                    this.guardarCarrito()
                }
            }
        },
        vaciarCarrito() {
            this.carrito.splice(0, this.carrito.length)
            this.guardarCarrito();
        },
        subtotal(producto) {
            return producto.precio * producto.cantidad
        },
        talles(){
            
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
        precioTotal() {
            return this.carrito.reduce((acum, producto) => {
                return acum += (producto.precio * producto.cantidad)
            }, 0)
        },
        totalProductosCarrito() {
            suma = this.carrito.reduce((acumulador, producto) => {
                return acumulador += producto.cantidad
            }, 0)
            return suma;
        }
    },
    mounted() {
        if (localStorage.getItem('carrito')) {
            try {
                this.carrito = JSON.parse(localStorage.getItem('carrito'));
            } catch (e) {
                localStorage.removeItem('carrito');
            }
        }
    }
});

let mount = app.mount("#app");