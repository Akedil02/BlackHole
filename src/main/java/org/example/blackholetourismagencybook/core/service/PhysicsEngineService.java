package org.example.blackholetourismagencybook.core.service;


import org.springframework.stereotype.Service;

@Service
public class PhysicsEngineService {

    //Schwarzschild Radius Constant
    private static final double RS_CONSTANT = 2.95;

    public double calculateEarthTime(double shipYears, double blackHoleMassSolar, double orbitRadiusKm) throws IllegalArgumentException {

        double schwarzschildRadius = RS_CONSTANT * blackHoleMassSolar;

        if(orbitRadiusKm <= schwarzschildRadius) {
            throw new IllegalArgumentException("Physical Constraint: The orbital radius must be greater than the event horizon (Schwarzschild radius:" + schwarzschildRadius + " km).");
        }

        double dilationFactor = Math.sqrt(1 - (schwarzschildRadius / orbitRadiusKm));


        return shipYears/dilationFactor;
    }


}
