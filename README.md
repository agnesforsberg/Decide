# **Decide**
This project is an implementation of a Launch Interceptor Program developed from the specification _Launch Interceptor Program: Requirements
Specification_ which is derived from _An experimental evaluation of the
assumption of independence in multiversion programming_ by J. C. Knight and
N. G. Leveson, IEEE Transactions on Software Engineering 12(1):96–109,
January 1986.

## How to use
The class ```LaunchInterceptor```  provides a method ```decide()``` which returns a boolean launch signal to the interceptor based on the input information. Such as radar information in the form of planar points, and configuration information in the form of the helper class ```Parameters```. Classes ```Point``` and ```Connector``` are also provided as helper classes but to use the system one does not need to use these directly if advanced use of the system is not wanted.

To use the ```LaunchInterceptor``` one has to create an instance of it, and provide the parameters:

- **numPoints**: The number of planar data points.
- **points**: Array containing the coordinates of data points.
- **parameters**: Struct holding parameters for LIC’s.
- **lcm**: 15x15 Logical Connector Matrix with ```Connector``` enunm types.
- **puv**: 15x1 boolean Preliminary Unlocking Vector containing the relevant LIC's.

## Requirements
    Junit: 4.13.1
    Java JDK: 11.0.9.1


## Contributions

- **Jakob Berggren**
  - Skeleton Code
  - LIC 4
  - LIC 9
  - LIC 14
- E-Joon Ko
    - Stuff
- Agnes Forsberg
    - Stuff
- Niklas Tomsic
  - Stuff
- Caroline Borg
  - Stuff
