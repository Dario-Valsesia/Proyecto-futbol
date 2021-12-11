const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            listaProductos: [],
            listaReservas: [],
            listaPendientes: [],
            listaCompletadas: [],
            camiseta: "",
            short: "",
            balon: "",
            botin: "",
            lengthText: 0,
            data:{
                nombre: "",
                email: ""
            },
            consulta:{
                nombre: "",
                email: "",
                asunto: "",
                mensaje: ""
            }
        }
    },
    created(){
        this.datosClienteActual()
        this.productos()
    },
    methods:{
        datosClienteActual(){
            axios.get("/api/cliente/actual")
            .then(response => {
                this.datosCliente = response.data
                this.listaReservas = response.data.reservas

                this.ordenarReservas(this.listaReservas)
            })
        },
        subscripcion(){
            swal({
                title: "¡Atención!",
                text: "¿Estás seguro que quieres subscribirte a nuestro newsletter?",
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
        consultas(){
            axios.post("/api/consulta",`nombre=${this.consulta.nombre}&email=${this.consulta.email}&asunto=${this.consulta.asunto}&mensaje=${this.consulta.mensaje}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                swal({
                    title: "¡Confirmación!",
                    text: "Gracias por comunicarse con nosotros. Su consulta fue enviada.",
                    icon: "success"
                })
                .then(confirmation => {
                    window.location.reload()
                })
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
        productos(){
            axios.get("/api/productos")
            .then(response => {
                this.listaProductos = response.data
            })
        },
        ordenarReservas(array){
            return array.sort((a, b) => {
                if(a.horaIngreso > b.horaIngreso){
                    return -1
                }else if(a.horaIngreso < b.horaIngreso){
                    return 1
                }else{
                    return 0
                }
            })
        },
        momentFecha(date){
            return moment(date).format("DD/MM/YYYY")
        },
        momentHora(date){
            return moment(date).format("HH:mm")
        },
        numberFormat(data){
            return numeral(data).format("$ 0,0.00")
        },
        cancelarReserva(id){
            const urlParams = new URLSearchParams(window.location.search);
            this.reservaID = id;

            swal({
                title: "Confirmación",
                text: "¿Estás seguro que quieres cancelar su reserva?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/reservas/${this.reservaID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            text: "Reserva cancelada.",
                            icon: "success",
                            button: "Volver"
                        })
                        .then(response =>{
                            window.location.replace("home.html")
                        })
                    })
                }
            })
        },
        eliminarReserva(id){
            const urlParams = new URLSearchParams(window.location.search);
            this.reservaID = id;

            swal({
                title: "Confirmación",
                text: "¿Estás seguro que quieres eliminar esta reserva?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.delete(`/api/reservas/${this.reservaID}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            text: "Reserva eliminada.",
                            icon: "success",
                            button: "Volver"
                        })
                        .then(response =>{
                            window.location.replace("home.html")
                        })
                    })
                }
            })
        }
    },
    computed:{
        filtrarPendientes(){
            this.listaPendientes = this.listaReservas.filter(reserva => this.momentFecha(reserva.horaIngreso) >= this.momentFecha(new Date))
            return this.listaPendientes
        },
        filtrarCompletadas(){
            this.listaCompletadas = this.listaReservas.filter(reserva => this.momentFecha(reserva.horaIngreso) < this.momentFecha(new Date))
            return this.listaCompletadas
        },
        filtrarCamiseta(){
            this.camiseta = this.listaProductos.filter(camiseta => camiseta.nombreProducto === "Camiseta Argentina")
            return this.camiseta
        },
        filtrarShort(){
            this.short = this.listaProductos.filter(short => short.nombreProducto === "Short Barcelona Rojo")
            return this.short
        },
        filtrarBalon(){
            this.balon = this.listaProductos.filter(balon => balon.nombreProducto === "Pelota Mundial 2018")
            return this.balon
        },
        filtrarBotin(){
            this.botin = this.listaProductos.filter(botin => botin.nombreProducto === "Botin")
            return this.botin
        },
        counter(){
            return this.lengthText + this.consulta.mensaje.length
        }
    }
})
const consola = app.mount("#app")