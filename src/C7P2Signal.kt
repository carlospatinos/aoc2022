import java.io.File

// This solution worked for both challenges just changing marker position
fun main() {
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
    //c7input
    File("./tmp.txt").forEachLine {

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
                println("exploring directory ${currentNode.name}")
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

    println("Total size is ${rootNode.totalSize}")
    var count = 0
    for(dir in dirStructure) {
        if (dir.totalSize <= 100000){
            count += dir.totalSize
            println("Dir ${dir.name} is ${dir.totalSize} in size, so being added")
        }
    }
    println("count = $count")

}


