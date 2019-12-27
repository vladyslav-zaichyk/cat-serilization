# cat-serialization

## Serialization
**Serialization** is the conversion of the state of an object into a byte stream; deserialization does the opposite. 
Classes that are eligible for serialization need to implement a special marker interface _Serializable_ or _Externalizable_.        
___
## Serial Version UID
The JVM associates a version (long) number with each serializable class. It is used to verify that the saved and loaded objects have the same attributes and thus are compatible on serialization.      
___

## Nonserializable Objects
* **Rule 1**: The object to be persisted must implement the Serializable interface or inherit that implementation from its object hierarchy
* **Rule 2**: The object to be persisted must mark all nonserializable fields transient

## Customize the Default Protocol
By using a built-in feature of the serialization mechanism, developers can enhance the normal process by providing two methods inside their class files. Those methods are:     
* _private void writeObject(ObjectOutputStream out)_
* _private void readObject(ObjectInputStream in)_
___
##  The Externalizable Interface
The third option for serialization: you can create your own protocol with the _Externalizable_ interface. Instead of implementing the _Serializable_ interface, you can implement _Externalizable_, which contains two methods:     
* _public void writeExternal(ObjectOutput out)_
* _public void readExternal(ObjectInput in)_
___
## Gotchas
### Caching Objects in the Stream
By default, an ObjectOutputStream will maintain a reference to an object written to it. That means that if the state of the written object is written and then written again, the new state will not be saved!
There are two ways to control that situation. First, you could make sure to always close the stream after a write call, ensuring the new object is written out each time. Second, you could call the ObjectOutputStream.reset() method, which would tell the stream to release the cache of references it is holding so all new write calls will actually be written.

## Performance Considerations
There is often a trade-off between convenience and performance, and serialization proves no different. If speed is the primary consideration for your application, you may want to consider building a custom protocol.     Another consideration concerns the aforementioned fact that object references are cached in the output stream. Due to that, the system may not garbage collect the objects written to a stream if the stream is not closed. The best move, as always with I/O, is to close the streams as soon as possible, following the write operations.


