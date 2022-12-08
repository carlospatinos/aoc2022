import java.io.File

// This solution worked for both challenges just changing marker position
fun main() {

    val TOTAL_FILESYSTEM_SPACE = 70000000
    val SPACE_REQUIRED_FOR_UPDATE = 30000000

    class Node {
        var totalSize:Int = 0
        var size:Int = 0
        var isDirectory = false;
        var isRoot = false;
        var name:String = "";
        var parent:Node? = null
        var children = mutableListOf<Node>()

        // For directories
        constructor (providedName:String){
            name = providedName
            if (name == "/") {
                isRoot = true;
            }
            isDirectory = true
        }

        // For directories
        constructor (providedName:String, providedParent:Node){
            name = providedName
            isDirectory = true
            parent = providedParent
        }

        // For files
        constructor (providedName:String, providedSize:Int){
            name = providedName
            size = providedSize
        }

        constructor (providedName:String, providedParent:Node, providedSize:Int){
            name = providedName
            size = providedSize
        }

        fun updateTotalSize(fileSize:Int){
            totalSize += fileSize
        }
    }


    var rootNode = Node("/")
    var currentNode = rootNode
    var dirStructure = mutableListOf<Node>()
    dirStructure.add(rootNode)
    //
    File("./c7input.txt").forEachLine {

        if(it.isEmpty() || it.isBlank()){
            throw Exception("Invalid content")
        }
        // Command
        if(it.startsWith("$")){
            if(it.startsWith("$ cd")){
                val directionOrName = it.substring(5)
                if(directionOrName == ".." && !currentNode.isRoot){
                    currentNode = currentNode.parent!!
                } else {
                    if (currentNode.name != directionOrName) {
                        for (element in currentNode.children){
                            if (element.name == directionOrName) {
                                currentNode = element
                                break
                            }
                        }
                    }
                }
            } else if(it.startsWith("$ ls")){
//                println("exploring directory ${currentNode.name}")
            }
        } else {
            val fileDescription = it.split(" ")
            if(fileDescription[0].startsWith("dir")){
                val newDir = Node(fileDescription[1], currentNode)
                currentNode.children.add(newDir)
                dirStructure.add(newDir)
            } else {
                val fileSize = fileDescription[0].toInt();
                currentNode.children.add(Node(fileDescription[1], currentNode, fileSize))
                currentNode.updateTotalSize(fileSize)

                var tmpNode = currentNode
                while(tmpNode.parent != null) {
                    tmpNode = tmpNode.parent!!
                    tmpNode.updateTotalSize(fileSize)
                }
            }
        }

    }

    println("Total space used is ${rootNode.totalSize}")
    var freeSpace = TOTAL_FILESYSTEM_SPACE - rootNode.totalSize
    println("Free space is ${freeSpace}")
    var spaceNeeded = SPACE_REQUIRED_FOR_UPDATE - freeSpace
    println("Extra space needed for the update ${spaceNeeded}")

    var listOfDirectoriesToDelete = mutableListOf<Node>()
    for(dir in dirStructure) {
        if (dir.totalSize >= spaceNeeded){
            listOfDirectoriesToDelete.add(dir)
            println("Dir ${dir.name} is ${dir.totalSize} in size, so being added")
        }
    }

    listOfDirectoriesToDelete.sortBy { it.totalSize }

    val winner = listOfDirectoriesToDelete.get(0)
    println("Dir to delete is ${winner.name} with size ${winner.totalSize}")

}


