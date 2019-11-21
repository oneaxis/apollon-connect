package de.oneaxis.apollon.connect.application.instrument;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("instruments")
class InstrumentController {

    private final InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @PostMapping
    public InstrumentRest saveInstrument(@RequestBody InstrumentRest instrumentRest) {
        return this.instrumentService.saveInstrument(instrumentRest);
    }
}
