const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            data:{
                nombre: "",
                email: ""
            },
            /////////////
            meses:['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre','Octubre', 'Noviembre', 'Diciembre'],
            fechaActual:"",
            numeroMes: "",    
            diaActual:'',
            verCalendario:false,
            ////////////
            canchas:[],
            horarios:["17","18","19","20","21","22","23"],
            reservas:[],
            horariosReservados:[],
            verHorarios:false,
            verConfirmacion:false,
            //Lo que selecciona el usuario
            year: "",
            idCancha:0,
            diaSeleccionado:"",
            horaSeleccionada:'',
            fechaArray:[],
            fechaCompleta:'',
            //MENSAJE DE ERROR
            mensajeError:'',
        }
    },
    created(){
        this.datosClienteActual();
        this.cargarFecha();
        this.cargarCanchas();
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
        cargarCanchas(){
            axios.get('/api/canchas')
            .then(res=>{
                this.canchas = res.data;            
            })
            .catch(e=>console.error(e))
        },
        canchaSeleccionada(e){
            this.idCancha = parseInt(e.target.value); 
            this.verCalendario=true;
            this.verHorarios = false;
            this.verConfirmacion = false;
        },
        fechaSeleccionada(e){
            this.diaSeleccionado = e.target.dataset.id
            let fechaDeHoy = new Date()
            if((parseInt(this.diaSeleccionado)<this.diaActual&&this.numeroMes<=fechaDeHoy.getMonth()&&this.year<=fechaDeHoy.getFullYear())||this.numeroMes<fechaDeHoy.getMonth()&&this.year<=fechaDeHoy.getFullYear()){
                 this.verHorarios=false;
                 swal({
                    text: "Imposible reservar dias anteriores a la fecha de hoy",
                    icon: "warning",
                });
            }else{
                this.verHorarios=false;         
                let mesSeleccionado = this.numeroMes + 1 ;
                this.fechaArray = [parseInt(this.year) ,mesSeleccionado,parseInt(this.diaSeleccionado)];
                axios.get(`/api/reservas?id=${this.idCancha}&integers=${this.fechaArray}`).then(res=>{
                this.reservas=res.data;
                this.horariosReservados = this.reservas.map(reserva=>{
                    return reserva.horaIngreso.slice(11,13);
                })
                this.verHorarios=true;
            }).catch(e=>console.log(e));
            }
            
        },
        confirmarReserva(e){
            this.horaSeleccionada = e.target.value;
            scrollTo(0,0);
            this.verCalendario=false;
            this.verConfirmacion=true;
        },
        realizarReserva(){   
            if(this.diaSeleccionado<10){
                this.diaSeleccionado = "0"+this.diaSeleccionado;
            }
            if(this.numeroMes<9){
                this.numeroMes++
                this.numeroMes = "0"+this.numeroMes;
            }else{
                this.numeroMes++
            }
            this.fechaCompleta = `${this.year}-${this.numeroMes}-${this.diaSeleccionado}T${this.horaSeleccionada}:00:00`
            axios.post('/api/reservar',`fechaHora=${this.fechaCompleta}&id=${this.idCancha}`,{responseType: 'blob'}).then(res=>{
                let disposition = res.headers['content-disposition'];
                let fileName = decodeURI(disposition.substring(21));
                let link = document.createElement('a');
                link.href = window.URL.createObjectURL(res.data);
                link.download = fileName;
                link.click();
                link.remove();
                swal({
                    text: "Reserva realizada correctamente",
                    icon: "success",
                });
                setTimeout(()=>location.reload(),1500);           
            }).catch(e=>this.mensajeError=e.response.data);
        },
        cancelarReserva(){
            location.reload()
        },


        //METODOS PARA EL CALENDARIO
        mesAnterior(){
            if(this.numeroMes !== 0){
                this.numeroMes--;
            }else{
                this.numeroMes = 11;
                this.year--;
            }
        },
        mesSiguiente(){
            if(this.numeroMes !== 11){
                this.numeroMes++;
            }else{
                this.numeroMes = 0;
                this.year++;
            }
        },
        cargarFecha(){
            this.fechaActual= new Date()
            this.numeroMes= this.fechaActual.getMonth()
            this.year= this.fechaActual.getFullYear()
            this.diaActual = this.fechaActual.getDate();
        },
        diaComienza(){
            let comienza = new Date(this.year, this.numeroMes, 1);
            return ((comienza.getDay()-1) === -1) ? 6 : comienza.getDay()-1;
        },
        esBisiesto(){
            return ((this.year % 100 !==0) && (this.year % 4 === 0) || (this.year % 400 === 0))
        },
        diasTotalMes(numeroMes){
            if (numeroMes == 0 ||numeroMes == 2 || numeroMes == 4 || numeroMes == 6 || numeroMes == 7 || numeroMes == 9 || numeroMes == 11) {
               return 31; 
            } else if (numeroMes == 3 || numeroMes == 5 || numeroMes == 8 || numeroMes == 10) {
               return 30;
            } else {  
               return this.esBisiesto() ? 29:28;
            }
        },
        escribirDias(){
            let arrayPrueba = [];
            for(let i = this.diaComienza();i>0;i--){
              arrayPrueba.push(this.diasTotalMes(this.numeroMes-1)-(i-1));               
            }
            return arrayPrueba;          
        },
        //FIN METODOS CALENDARIO
        
      
    },
    computed:{
        mesSelec(){
            return this.meses[this.numeroMes];
        },
        nombreCancha(){
            let cancha =  this.canchas.filter(cancha=>cancha.id==this.idCancha);
            return (parseInt(cancha[0].cantidadJugadores) / 2);
        },
        precioCancha(){
            let cancha =  this.canchas.filter(cancha=>cancha.id==this.idCancha);
            return cancha[0].precio;
        }
              
    }
});

let mount = app.mount("#app");