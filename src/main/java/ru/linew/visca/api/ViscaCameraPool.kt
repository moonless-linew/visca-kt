package ru.linew.visca.api


//not ready
class ViscaCameraPool {

    private val cameras = mutableListOf<ViscaCamera>()

    suspend fun createNewConnection(ip: String, port: Int) {
        val camera = ViscaCamera(ip, port)
        camera.connect()
        cameras.add(camera)
    }

    operator fun get(index: Int): ViscaCamera {
        return cameras[index]
    }
}