const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            data:{
                nombre: "",
                email: ""
            },

            /* busqueda */
            busqueda:"",
            noExiste: false,
            productos:[],
            //FILTROS
            filtroProductos:[],
            filtroPrecio:'',
            /* carrito */
            carrito: [],
            talleSeleccionado:'',
            /* pago carrito */
            verDatosTarjeta:false,
            numeroDeTarjeta:"",
            titularTarjeta:"",
            vencimiento:"",
            cvc:0,
            //MENSAJES
            errorMensaje:'',
            mensajeCorrecto:''

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
        subscripcion(){
            swal({
                title: "¡Atención!",
                text: "¿Estás seguro/a que quieres subscribirte a nuestro newsletter?",
                icon: "warning",
                buttons: [true, "Continuar"]
            })
            .then(confrimation => {
                axios.post("/api/mail",`destino=${this.data.email}&nombreUsuario=${this.data.nombre}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    swal({
                        title: "¡Genial!",
                        text: "Gracias por subscribirte a nuestro newsletter.",
                        icon: "success"
                    })
                    .then(confirmation => {
                        window.location.reload()
                    })
                })
            })
        },
        signOut(){
            swal({
                text: "¿Estás seguro/a que quieres cerrar su sesión?",
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
                this.productos = localStorage.getItem("productosModificados") ? JSON.parse(localStorage.getItem("productosModificados")) : res.data;
                this.carrito = localStorage.getItem("carrito") ? JSON.parse(localStorage.getItem("carrito")) : [];
            })
        },
        cancelarCompra(){
            this.verDatosTarjeta=false;
        },
        mostrarDatosTarjeta(){
            let botonCerrar = this.$refs.cerrar;
            botonCerrar.click();
            scrollTo(0,0);
            this.verDatosTarjeta=true;
        },
        comprarProductos(){
            axios.post('https://mbbhomebanking.herokuapp.com/api/pagar',`numCard=${this.numeroDeTarjeta}&cvv=${this.cvc}&name=${this.titularTarjeta}&thruDate=${this.vencimiento}&total=${this.precioTotal}`).then(res=>{
                this.carrito.forEach(producto=>{
                    axios.post('/api/comprar/producto',`id=${producto.id}&talle=${producto.talleSeleccionado}&cantidad=${producto.cantidad}`).then(res=>{
                        this.errorMensaje='';
                        this.mensajeCorrecto = 'Compra realizada con exito';
                    }).catch(e=>console.log(e));
                })
                this.vaciarCarrito(); 
                setTimeout(()=>location.reload(),1500);
            }).catch(e=>{
                this.errorMensaje=e.response.data
            })
            
            
        },
        guardarCarrito() {
            localStorage.setItem("productosModificados", JSON.stringify(this.productos));
            localStorage.setItem("carrito", JSON.stringify(this.carrito));         
        },
        agregarCarrito(e){
            if(this.talleSeleccionado !='' && this.talleSeleccionado != "Seleccione su talle"){
                let idProducto = e.target.value;
                this.productos.forEach(producto => {
                    if(producto.id == idProducto && producto.stock>0){
                        producto.stock--
                        if(producto.stock === 0){
                            swal({
                                text: "Agregaste la última unidad de este producto.",
                                icon:"warning",
                                buttons: "ok"
                            })
                        }
                        let productoSeleccionado = {
                            id:producto.id,
                            nombreProducto:producto.nombreProducto,
                            talleSeleccionado: this.talleSeleccionado,
                            cantidad:1,
                            precio:producto.precio, 
                            url:producto.urlImg,                          
                        }
                        if(this.carrito.length==0){
                            swal({
                                text: "El producto se agrego correctamente al carrito",
                                icon:"success",
                                buttons: "Ok"
                            })
                            this.carrito.push(productoSeleccionado);
                            this.guardarCarrito();
                        }else{                           
                            this.carrito.forEach(prodCarrito=>{
                                if(prodCarrito.id == productoSeleccionado.id && prodCarrito.talleSeleccionado == this.talleSeleccionado){
                                   prodCarrito.cantidad++;
                                }else if(prodCarrito.id == productoSeleccionado.id && prodCarrito.talleSeleccionado !=this.talleSeleccionado){
                                   this.carrito.push(productoSeleccionado);
                                }
                            })
                            let prueba = this.carrito.filter(prod=>prod.id==productoSeleccionado.id)
                            if(prueba.length==0){
                                this.carrito.push(productoSeleccionado);
                            }                                              
                            swal({
                                text: "El producto se agrego correctamente al carrito",
                                icon:"success",
                                buttons: "Ok"
                            })
                            this.guardarCarrito();
                        }
                        
                        
                    }
                });
                
            }else{
                swal({
                    text: "Seleccione talle",
                    icon:"warning",
                    buttons: "Ok"
                })
            }          
        },
        quitarCarrito(e){
            console.log(e.target.value);
            let idProducto = e.target.value;
            let talle = e.target.dataset.id
            this.carrito.forEach(prodCarrito=>{
                if(prodCarrito.id == idProducto && prodCarrito.talleSeleccionado == talle){                 
                    if(prodCarrito.cantidad>1){
                        prodCarrito.cantidad--;                      
                    }
                    else{
                        prodCarrito.cantidad--
                        this.carrito = this.carrito.filter(a=>a.cantidad>0);
                    }
                    this.productos.forEach(prod=>{
                        if(prod.id==idProducto){
                            prod.stock++
                        }
                    })
                }
                this.guardarCarrito();
                if(this.carrito.length==0){
                    localStorage.removeItem("productosModificados");
                    localStorage.removeItem("carrito");
                }
            })
            
        },
        obtenerTalle(e){
            this.talleSeleccionado = e.target.value;
        }, 
       
        vaciarCarrito() {
            this.carrito = [];
            localStorage.removeItem("productosModificados");
            localStorage.removeItem("carrito");
        },
        subtotal(producto) {
            return producto.precio * producto.cantidad
        },
        
       

    },
    computed:{
       
        FiltroDeProductos(){
            if(this.filtroProductos.length==0 && this.busqueda =='' && this.filtroPrecio ==''){
                return this.productos
            }
            if(this.busqueda != ''){
                let filtroBusqueda = this.productos.filter(prod=>prod.nombreProducto.toLowerCase().includes(this.busqueda.toLowerCase()))
                if(filtroBusqueda.length==0){
                    return this.noExiste = true;
                }
                if(this.filtroPrecio==''){ 
                    return filtroBusqueda;
                }else if(this.filtroPrecio=="menor"){
                    return filtroBusqueda.sort((a,b)=>{
                        if(a.precio<b.precio){
                            return -1
                        }
                        if(a.precio>b.precio){
                            return 1
                        }
                        return 0
                    })
                }else{
                    return filtroBusqueda.sort((a,b)=>{
                        if(a.precio<b.precio){
                            return 1
                        }
                        if(a.precio>b.precio){
                            return -1
                        }
                        return 0
                    })
                }
                
            }         
            if(this.filtroProductos.length>0 && this.filtroPrecio != ''){
                let filtro = this.productos.filter(prod=>{
                    return this.filtroProductos.includes(prod.name);
                })
                if(this.filtroPrecio == 'menor'){
                    filtro.sort((a,b)=>{
                        if(a.precio<b.precio){
                            return -1
                        }
                        if(a.precio>b.precio){
                            return 1
                        }
                        return 0
                    })
                    return filtro
                }else{
                    filtro.sort((a,b)=>{
                        if(a.precio<b.precio){
                            return 1
                        }
                        if(a.precio>b.precio){
                            return -1
                        }
                        return 0
                    })
                    return filtro
                }
               
            }   
            if(this.filtroProductos.length>0){
                let filtro = this.productos.filter(prod=>{
                    return this.filtroProductos.includes(prod.name);
                })
                return filtro;
            }
            
            if(this.filtroPrecio!=''){
                if(this.filtroPrecio=='menor'){
                    this.productos.sort((a,b)=>{
                        if(a.precio<b.precio){
                            return -1
                        }
                        if(a.precio>b.precio){
                            return 1
                        }
                        return 0 
                    })
                    return this.productos
                }
                if(this.filtroPrecio=='mayor'){
                    this.productos.sort((a,b)=>{
                        if(a.precio<b.precio){
                            return 1
                        }
                        if(a.precio>b.precio){
                            return -1
                        }
                        return 0 
                    })
                    return this.productos
                }
            }  

        },
        pintarCarrito(){
            let carrito = this.productos.filter(producto=>producto.cantidad>0);
            return carrito;
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
});

let mount = app.mount("#app");