package com.triplerock.codeforce.data

enum class RoomType {
    Empty,
    Developer,
    Manager,
    Sales,
    HR,
    Cafeteria
}

enum class MaxCapacity(val capacity: Int) {
    Developer(4),
    Manager(2),
    HR(1),
    Sales(2),
    Cafeteria(1),
}

fun maxCapacityOf(roomType: RoomType) = when (roomType) {
    RoomType.Developer -> MaxCapacity.Developer.capacity
    RoomType.Manager -> MaxCapacity.Manager.capacity
    RoomType.HR -> MaxCapacity.HR.capacity
    RoomType.Sales -> MaxCapacity.Sales.capacity
    RoomType.Cafeteria -> MaxCapacity.Cafeteria.capacity
    RoomType.Empty -> 0
}

enum class Cost(val cost: Int) {
    Developer(40000),
    Manager(15000),
    HR(10000),
    Sales(10000),
    Cafeteria(5000),
}

fun costOf(roomType: RoomType) = when (roomType) {
    RoomType.Developer -> Cost.Developer.cost
    RoomType.Manager -> Cost.Manager.cost
    RoomType.HR -> Cost.HR.cost
    RoomType.Sales -> Cost.Sales.cost
    RoomType.Cafeteria -> Cost.Cafeteria.cost
    RoomType.Empty -> 0
}

fun Int.displayCost(): String {
    return "₹${"%,d".format(this)}"
}

fun roomDescription(roomType: RoomType): String {
    return when (roomType) {
        RoomType.Developer -> "Room for developers with max capacity of ${maxCapacityOf(roomType)}. Room is equipped with Laptops, Desktops, Hardwares, Cantools etc"
        RoomType.Manager -> "Room for managers with max capacity of ${maxCapacityOf(roomType)}. Room is equipped with Laptops, Desktops, Hardwares, Cantools etc"
        RoomType.HR -> "Room for HR reps with max capacity of ${maxCapacityOf(roomType)}. Room is equipped with Laptops, Records and game toys"
        RoomType.Sales -> "Room for Sales executives with max capacity of ${maxCapacityOf(roomType)}. Room is equipped with Laptops"
        RoomType.Cafeteria -> "Room for employees to relax and eat. There will be house keeps with max capacity of ${
            maxCapacityOf(
                roomType
            )
        }. Room is equipped with Ovens, Sinks, Fridge, Vending machines"

        else -> "Empty room"
    }
}

data class Room(
    val type: RoomType = RoomType.Empty,
    val count: Int = 0,
    val capacity: Int = 4,
    val x: Int = -1,
    val y: Int = -1
)

val sampleRooms = listOf(
    Room(x = 2, y = 2, type = RoomType.Manager),
    Room(x = 2, y = 1, type = RoomType.Developer),
    Room(x = 1, y = 1, type = RoomType.Developer),
    Room(x = 1, y = 2, type = RoomType.Developer),
    Room(x = 2, y = 3, type = RoomType.Cafeteria),
    Room(x = 3, y = 2, type = RoomType.Sales),
)